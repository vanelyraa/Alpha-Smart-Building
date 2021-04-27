package grpc.services.light;

/*import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class LightClient {

	private static LightServiceGrpc.LightServiceBlockingStub lblockingStub;
	private static LightServiceGrpc.LightServiceStub lasyncStub;
	
	public static class Listener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent serviceEvent) {
            System.out.println("Service added: " + serviceEvent.getInfo());
        }

        @Override
        public void serviceRemoved(ServiceEvent serviceEvent) {
            System.out.println("Service removed: " + serviceEvent.getInfo());
        }

        @Override
        public void serviceResolved(ServiceEvent serviceEvent) {
            System.out.println("Service resolved: " + serviceEvent.getInfo());
            ServiceInfo info = serviceEvent.getInfo();
            final int Port = serviceEvent.getInfo().getPort();
            String address = info.getHostAddresses()[0];
            //String address = "localhost";
            
            
        }
    }
		
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ManagedChannel lightchannel = ManagedChannelBuilder.forAddress("localhost", 50097).usePlaintext().build();

		lblockingStub = LightServiceGrpc.newBlockingStub(lightchannel);
		lasyncStub = LightServiceGrpc.newStub(lightchannel);
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Add a service listener
			jmdns.addServiceListener("_http._tcp.local.", new Listener());

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		lighting();
		LightsOnOff();
		lightIntensity();
	}
	
	public static void lighting(){
		Empty emp = Empty.newBuilder().build();
		
		System.out.println("Lights");
		LightingResponse response;
		//Error Handling
		try {
			 response = lblockingStub.lighting(emp);
			 String intens = String.valueOf(response.getIntensity());
				System.out.println("LightID: " + response.getLightId());
				System.out.println("Light status: " + response.getStatus());
				System.out.println("Brightness: " + intens);
		}catch(io.grpc.StatusRuntimeException e) {
			System.out.println("RPC lighiting failed:"+ e.getStatus());
			return;
		}	
	}

	public static void LightsOnOff(){
		LightsRequest request = LightsRequest.newBuilder().setSwitch(false).build();
		
		// check the response
		LightsResponse response = lblockingStub.lightsOnOff(request);
		
		// print appropriate message depending on response
		if (response.getSwitch()) {
			System.out.println("Lights turned on!");
		}
		else {
			System.out.println("Lights off!");
		}
	}
	
	public static void lightIntensity(){
		StreamObserver<IntensityResponse> responseObserver = new StreamObserver<IntensityResponse>() {

			@Override
			public void onNext(IntensityResponse intens) {
				// Print out response
				System.out.println("Brightness has been set to level " + intens.getIntensity());
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {

			}
		};

		StreamObserver<IntensityRequest> requestObserver = lasyncStub.lightIntensity(responseObserver);
		try {
			// send a stream of requests
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(1).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(3).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(2).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(5).build());
			System.out.println("Lights brightness changed");
			
			
			Thread.sleep(new Random().nextInt(2000) + 1000);
			// catch any errors
		} catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
		requestObserver.onCompleted();
	}
	
}*/

