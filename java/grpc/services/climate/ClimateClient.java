package grpc.services.climate;

import grpc.services.climate.ClimateServiceGrpc.ClimateServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClimateClient {
	
	private static ClimateServiceGrpc.ClimateServiceBlockingStub blockingStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		blockingStub = ClimateServiceGrpc.newBlockingStub(channel);

		//Create Request messages for use within the main method
		

		//Call the method RPC from within main
		

		//Call the method RPC from within main
		

		//Call the login RPC from  method
	

		//Call the logout RPC from  method
		
	}
}
