package grpc.services.climate;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClimateClient {
	
	private static ClimateServiceGrpc.ClimateServiceBlockingStub blockingStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

		blockingStub = ClimateServiceGrpc.newBlockingStub(channel);

		
	}

}
