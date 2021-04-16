package grpc.services.occupancy;

import grpc.services.occupancy.OccupancyServiceGrpc.OccupancyServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OccupancyClient {
	
	private static OccupancyServiceGrpc.OccupancyServiceBlockingStub blockingStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		blockingStub = OccupancyServiceGrpc.newBlockingStub(channel);

		//Create Request messages for use within the main method
		

		//Call the method RPC from within main
		

		//Call the method RPC from within main
		

		//Call the login RPC from  method
	

		//Call the logout RPC from  method
		
	}
}
