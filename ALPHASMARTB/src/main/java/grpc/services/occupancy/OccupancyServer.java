package grpc.services.occupancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import grpc.services.climate.CoLevels;
import grpc.services.climate.ExtractionResponse;
import grpc.services.occupancy.OccupancyServiceGrpc.OccupancyServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OccupancyServer extends OccupancyServiceImplBase  {
	
public static void main(String[] args) {
		
		System.out.println("Starting gRPC Occupancy Server");
		OccupancyServer occupancyserver = new OccupancyServer();//.build()  ??

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
	
	private List<ListResponse> listResponse;
	


	@Override
	public void getOccupancy(LocalRequest request, StreamObserver<OccupancyResponse> responseObserver) {
		for (grpc.services.occupancy.Occupancy occupancy : Occupancys.getInstance()) {
	        if (occupancy.getRoom() == request.getRoom()) {
	        	OccupancyResponse response = OccupancyResponse.newBuilder().setOccupancy(occupancy).build();
	        	responseObserver.onNext(response);
	        	responseObserver.onCompleted();
	            return;
	        }
		}
	}
	

	@Override
	public void switchCameraOn(CameraSwitchRequest request, StreamObserver<CameraResponse> responseObserver) {
System.out.println("Sensor captured motion in room");
		
		boolean cameraOn = request.getCameraOn();
		
		if(cameraOn) {
			System.out.println("Turn on camera...");
		}
		else {
			System.out.println("Devices off camera...");
		}
				
		CameraResponse response = CameraResponse.newBuilder().setCameraOn(cameraOn).build();
				
		responseObserver.onNext(response);
		responseObserver.onCompleted();

	}

	@Override
	public StreamObserver<FindListDay> getVisitList(StreamObserver<ListResponse> responseObserver) {
		listResponse = new ArrayList<ListResponse>();
		ListResponse visitor1 = ListResponse.newBuilder().setDay("Monday").setList("Yan Connor").build();
		ListResponse visitor2 = ListResponse.newBuilder().setDay("Tuesday").setList("Ben Harper").build();
		ListResponse visitor3 = ListResponse.newBuilder().setDay("Wednesday").setList("Kristian Coton").build();
		ListResponse visitor4 = ListResponse.newBuilder().setDay("Thursday").setList("Janice Limer").build();
		ListResponse visitor5 = ListResponse.newBuilder().setDay("Thursday").setList("Daniela Rojas").build();
		
		listResponse.add(visitor1);
		listResponse.add(visitor2);
		listResponse.add(visitor3);
		listResponse.add(visitor4);
		listResponse.add(visitor5);
		
		for (ListResponse person : listResponse) {
			if (person.getDay() == request.getDay()){ //
				continue;
			}
			String day = Person.getDay();
			String list = Person.getList();
			
			responseObserver.onNext(Person);
			
			System.out.println(Person);
		}
		responseObserver.onCompleted();

		//return super.getVisitList(responseObserver);
	}

	
}
