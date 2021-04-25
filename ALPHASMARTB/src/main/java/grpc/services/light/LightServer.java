package grpc.services.light;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import grpc.services.light.LightServiceGrpc.LightServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class LightServer extends LightServiceImplBase {
	
	public LightData myLightdata = new LightData();
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
	System.out.println("Starting gRPC Utilities Server");
	
	// Create & Register utilities service with jmDNS
	try {
		int PORT = 50053;
		JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_light._tcp.local.", "light", PORT, "Light server");
        jmdns.registerService(serviceInfo);
        LightServer lightServer = new LightServer();
        Server server = ServerBuilder.forPort(PORT)
                .addService(lightServer)
                .build()
                .start();
        System.out.println("Light server started, listening on " + PORT);
        server.awaitTermination();
        
	} catch (UnknownHostException e) {
		System.out.println(e.getMessage());
        e.printStackTrace();
	} catch (IOException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
	
}
	
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
