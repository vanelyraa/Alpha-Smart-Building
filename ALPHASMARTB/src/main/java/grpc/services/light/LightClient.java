package grpc.services.light;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class LightClient {

	private static LightServiceGrpc.LightServiceBlockingStub blockingStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();

		blockingStub = LightServiceGrpc.newBlockingStub(channel);

		
	}
}
