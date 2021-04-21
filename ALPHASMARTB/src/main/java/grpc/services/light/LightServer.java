package grpc.services.light;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.services.light.LightServiceGrpc.LightServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class LightServer extends LightServiceImplBase {
	
	public LightData myLightdata = new LightData();
		
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Server");
		

		try {
			int port = 50053;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	        ServiceInfo serviceInfo = ServiceInfo.create("_light._tcp.local.", "light", port, "Lights Service");
	        jmdns.registerService(serviceInfo);
	        LightServer lightserver = new LightServer();
			Server server = ServerBuilder.forPort(port)
				.addService(lightserver)
				.build()
				.start();

			System.out.println("Server started with Port:" + port);
			server.awaitTermination();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

	}//main

	@Override
	public void lighting(Empty request, StreamObserver<LightingResponse> responseObserver) {
		
		System.out.println("Receiving light request");
		String status;
		
		if(myLightdata.isOn()) {
			  status ="On";
		}
		else {
			  status ="Off";
		}
		
		String lId = myLightdata.gettypeId();
		String lStatus = status;
		Integer lIntensity = myLightdata.getIntensity();
		
		//print out
		LightingResponse response = LightingResponse.newBuilder().setLightId(lId).setStatus(lStatus).setIntensity(lIntensity).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
		
	}

	@Override
	public void lightsOnOff(LightsRequest request, StreamObserver<LightsResponse> responseObserver) {
		
		System.out.println("Request received to turn On/Off Lights");
		
		Boolean OnOffL = request.getSwitch();
		myLightdata.setOn(OnOffL);
		
		//print out
		LightsResponse response = LightsResponse.newBuilder().setSwitch(OnOffL).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<IntensityRequest> lightIntensity(StreamObserver<IntensityResponse> responseObserver) {
		
		return new StreamObserver<IntensityRequest>() {
			// Setting a variable to be used
			int intensity = 0;
			
			public void onNext(IntensityRequest value) {
				// Print the request when received
				intensity = value.getIntensity();
				System.out.println("Lights intensity changed to -> " + intensity);
			}
			
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			public void onCompleted() {
				// Send the response
				IntensityResponse response = IntensityResponse.newBuilder().setIntensity(intensity).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}
		};
	}
}
