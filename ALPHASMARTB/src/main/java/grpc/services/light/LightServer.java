package grpc.services.light;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import grpc.services.light.LightServiceGrpc.LightServiceImplBase;
import grpc.services.utility.UtilityServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

//SERVER SIDE IMPLEMENTATION
public class LightServer extends LightServiceImplBase {
	
	LightData myLightdata = new LightData();
	private static final Logger logger = Logger.getLogger(UtilityServer.class.getName());
	
public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Lights Server");
		
		try {
			//Defining the port
			int PORT = 50097;
			
			//Creating a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Adding a service listener
			ServiceInfo serviceLight = ServiceInfo.create("_http._tcp.local.", "light", 50097,"path=index.html");
	        jmdns.registerService(serviceLight);
	        
	      //getting a reference to the server
	        LightServer lightServer = new LightServer();
	        Server server = ServerBuilder.forPort(PORT)
	        	.addService(lightServer)
                .build()
                .start();
	        logger.info("Light server started, listening on " + PORT);
            server.awaitTermination();            
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }		
	}


	/**
	 * GRPC services
	 */

	//Lights	
	@Override
	public void lighting(Empty request, StreamObserver<LightingResponse> responseObserver) {
		
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
		
		LightingResponse response = LightingResponse.newBuilder().setLightId(lId).setStatus(lStatus).setIntensity(lIntensity).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();		
	}
	
	//Lights
	@Override
	public void lightsOnOff(LightsRequest request, StreamObserver<LightsResponse> responseObserver) {
		
		//notification of method invocation
		System.out.println("Receiving request to turn On/Off Lights");
		
		//if true, devices off otherwise, on
		Boolean OnOffL = request.getSwitch();
		if (OnOffL) {
        	System.out.println("Setting lights off!");
        }
        else {
        	System.out.println("Setting lights on!");
        }		
		
		LightsResponse response = LightsResponse.newBuilder().setSwitch(OnOffL).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	//Lights intensity
	@Override
	public StreamObserver<IntensityRequest> lightIntensity(StreamObserver<IntensityResponse> responseObserver) {
		
		return new StreamObserver<IntensityRequest>() {
			//initializing variable for intensity change 
			int intensity = 0;
			
			public void onNext(IntensityRequest value) {
				//variable set to the user input
				intensity = value.getIntensity();
				System.out.println("Lights intensity changed: " + intensity);
			}
			
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			//response to the variable
			public void onCompleted() {				
				IntensityResponse response = IntensityResponse.newBuilder().setIntensity(intensity).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}
		};
	}
}
