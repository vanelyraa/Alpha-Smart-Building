package ServerClient;

import grpc.services.energy.EnergyServiceGrpc;
import grpc.services.energy.EnergyServiceGrpc.EnergyServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnergyClient {
	
	private static EnergyServiceGrpc.EnergyServiceBlockingStub blockingStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		blockingStub = EnergyServiceGrpc.newBlockingStub(channel);

		//Create Request messages for use within the main method
		

		//Call the method RPC from within main
		

		//Call the method RPC from within main
		

		//Call the login RPC from  method
	

		//Call the logout RPC from  method
		
	}
}