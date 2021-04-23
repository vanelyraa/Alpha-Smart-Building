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
	public static int lightPort;
	private static final Logger logger = Logger.getLogger(LightServer.class.getName());
	
	private static class Listener implements ServiceListener {
		 
        public void serviceAdded(ServiceEvent event) {
            System.out.println("Service added: " + event.getInfo());
        }

        
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }

        
        public void serviceResolved(ServiceEvent event) {
        	System.out.println("Service resolved: " + event.getInfo());
            System.out.println("Get Name: " + event.getName()+" PORT: "+event.getInfo().getPort());
            
            //Start GRPC server with discovered device
            if(event.getName().equals("Light")) {
            	System.out.println("Found Light port: " + event.getInfo().getPort());
	       		try {
	       			lightPort = event.getInfo().getPort();
					startGRPC(event.getInfo().getPort());
	       		} 
	       		catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	       		catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
          

        }
    }
	
		
	public static void main(String[] args) throws IOException, InterruptedException {
		 startDiscovery();
			}
		
	public static void startDiscovery() throws IOException, InterruptedException {
		System.out.println("Starting light gRPC Server");
		

		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener("_http._tcp.local.", new Listener());
		    System.out.println("Listener working");
		    // Wait a bit
		    Thread.sleep(20000);
						
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

	}//main
	
	public int getLightPort() {
		return lightPort;
	}

	public void setLightPort(int lightPort) {
		LightServer.lightPort = lightPort;
	}
	
	public static void startGRPC(int portNumber) throws IOException, InterruptedException {
		LightServer lightServer = new LightServer();
		    
		Server server = ServerBuilder.forPort(portNumber).addService(lightServer).build().start();
		logger.info("LightServer started, listening on " + portNumber);		     
		server.awaitTermination();
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
