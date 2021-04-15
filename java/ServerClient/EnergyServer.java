package ServerClient;

import java.io.IOException;

import grpc.services.energy.DevicesOffRequest;
import grpc.services.energy.DevicesOffResponse;
import grpc.services.energy.EnergyServiceGrpc.EnergyServiceImplBase;
import grpc.services.energy.LightAdjustRequest;
import grpc.services.energy.LightsOnRequest;
import grpc.services.energy.LightsResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class EnergyServer extends EnergyServiceImplBase{
	
	public static void main(String[] args) {
		
		System.out.println("Starting gRPC Energy Server");
		EnergyServer energyserver = new EnergyServer();

		int port = 50053;

		try {
			Server server = ServerBuilder.forPort(port)
					.addService(energyserver)
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
	
	//DEVICES - Unary RPC implementation
	@Override
	public void switchDevices(DevicesOffRequest request, StreamObserver<DevicesOffResponse> responseObserver) {
		
		System.out.println("Receiving a request to turn off devices!");
		
		boolean deviceOff = request.getDeviceOff();
		
		if(deviceOff) {
			System.out.println("Turn off devices...");
		}
		else {
			System.out.println("Devices on...");
		}
				
		DevicesOffResponse response = DevicesOffResponse.newBuilder().setDeviceOff(deviceOff).build();
				
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	//LIGHTS - Unary RPC implementation
	@Override
	public void switchLightsOn(LightsOnRequest request, StreamObserver<LightsResponse> responseObserver) {
		System.out.println("Request received to turn lights on");
		
		int lightId = request.getLightId();
				
		LightsResponse.Builder response = LightsResponse.newBuilder();
		System.out.println("Turn on lights with ID: " + lightId);
				
		//for()
		response.setLight(1).setResponseMessage("Light ID:" + lightId + "on");
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<LightAdjustRequest> lightIntensity(StreamObserver<LightsResponse> responseObserver) {


		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	


	message Lights{
	int32 lightId = 1;
	string lightStatus = 2;
}

message LightsOnRequest {
	int32 lightId = 1;
}

message LightsResponse {
	Lights light = 1;
}

message LightAdjustRequest {
	int32 lightId = 1;
	int32 adjust = 2;
}*/



	
}
