package grpc.services.light;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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
		
	}

	public static void LightsOnOff(){
		
	}
	
	public static void lightIntensity(){
		
	}
}
