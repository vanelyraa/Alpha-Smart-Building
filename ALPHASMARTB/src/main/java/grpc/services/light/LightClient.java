package grpc.services.light;

import java.util.Random;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class LightClient {

	private static LightServiceGrpc.LightServiceBlockingStub blockingStub;
	private static LightServiceGrpc.LightServiceStub asyncStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();

		blockingStub = LightServiceGrpc.newBlockingStub(channel);
		asyncStub = LightServiceGrpc.newStub(channel);
		
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
			 response = blockingStub.lighting(emp);
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
		LightsResponse response = blockingStub.lightsOnOff(request);
		
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

		StreamObserver<IntensityRequest> requestObserver = asyncStub.lightIntensity(responseObserver);
		try {
			// send a stream of requests
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(50).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(60).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(80).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(100).build());
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
	
}

