package ServerClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import grpc.services.occupancy.CameraResponse;
import grpc.services.occupancy.CameraSwitchRequest;
import grpc.services.occupancy.FindListDay;
import grpc.services.occupancy.ListResponse;
import grpc.services.occupancy.LocalRequest;
import grpc.services.occupancy.OccupancyResponse;
import grpc.services.occupancy.OccupancyServiceGrpc;
import grpc.services.occupancy.OccupancyServiceGrpc.OccupancyServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OccupancyServer extends OccupancyServiceImplBase {
	
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
		ListResponse inTheRoom = ListResponse.newBuilder().setRoom("Conference").setLocalOccupancy(4).build();
		ListResponse inTheRoom2 = ListResponse.newBuilder().setRoom("Auditorium").setLocalOccupancy(10).build();
		ListResponse inTheRoom3 = ListResponse.newBuilder().setRoom("Office 1").setLocalOccupancy(3).build();
		ListResponse inTheRoom4 = ListResponse.newBuilder().setRoom("Office 2").setLocalOccupancy(2).build();
		
		
		listResponse.add(inTheRoom);
		listResponse.add(inTheRoom2);
		listResponse.add(inTheRoom3);
		listResponse.add(inTheRoom4);
				
		for (ListResponse Person : listResponse) {
			if (Person.getDay().equals(request.getDay())){ //((DayOrBuilder) request).getDay()))
				continue;
			}
			String day = Person.getDay();
			String list = Person.getList();
			
			responseObserver.onNext(Person);
			
			System.out.println(Person);
		}
		responseObserver.onCompleted();
		
	}
	
	message Personnel{
		string room = 1;
		int32 localOccupancy = 2;
	}

	message LocalRequest {
	 	string room = 1;
	}
	  
	message OccupancyResponse {
		Personnel personnel = 1;
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
		
		for (ListResponse Person : listResponse) {
			if (Person.getDay().equals(request.getDay())){ //((DayOrBuilder) request).getDay()))
				continue;
			}
			String day = Person.getDay();
			String list = Person.getList();
			
			responseObserver.onNext(Person);
			
			System.out.println(Person);
		}
		responseObserver.onCompleted();
		return null;
	}

}


