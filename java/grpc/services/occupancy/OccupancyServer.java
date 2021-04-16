package grpc.services.occupancy;

import java.io.IOException;

import grpc.services.occupancy.OccupancyServiceGrpc.OccupancyServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OccupancyServer extends OccupancyServiceImplBase {
	
	public static void main(String[] args) {
		
		System.out.println("Starting gRPC Occupancy Server");
		OccupancyServer occupancyserver = new OccupancyServer();

		int port = 50051;

		try {
			Server server = ServerBuilder.forPort(port)
					.addService(occupancyserver)
					.build()
					.start();

			System.out.println("Server started with Port:" + server.getPort());
		    server.awaitTermination();

		}//try
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

	}//main

	@Override
	public StreamObserver<LocalRequest> getOccupancy(StreamObserver<OccupancyLevel> responseObserver) {
		return;
	}

	@Override
	public void switchCameraOn(CameraSwitchRequest request, StreamObserver<CameraResponse> responseObserver) {
		
	}

	@Override
	public void getVisitorList(VisitorsRequest request, StreamObserver<VisitorResponse> responseObserver) {
	
	}

	
}
