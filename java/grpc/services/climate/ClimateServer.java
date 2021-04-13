package grpc.services.climate;

import java.io.IOException;

import grpc.services.climate.ClimateServiceGrpc.ClimateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateServer extends ClimateServiceImplBase {
	
	public static void main(String[] args) {
		
		System.out.println("Starting gRPC Climate Server");
		ClimateServer climateserver = new ClimateServer();

		int port = 50051;

		try {
			Server server = ServerBuilder.forPort(port)
					.addService(climateserver)
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
	
	/*
	 * HVAC 
	 */
	@Override
	public void showHVACStatus(HVACStatusRequest request, StreamObserver<HVACStatusResponse> responseObserver) {
		
	}

	@Override
	public StreamObserver<TempRequest> setHVACtemp(StreamObserver<TempResponse> responseObserver) {
	return;
	}

	@Override
	public StreamObserver<CoLevelRequest> checkCO(StreamObserver<CoLevelResponse> responseObserver) {
	return;	
	}
	
	

}
