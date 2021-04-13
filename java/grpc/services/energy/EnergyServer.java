package grpc.services.energy;

import java.io.IOException;
import io.grpc.ServerBuilder;
import grpc.services.energy.EnergyServiceGrpc.EnergyServiceImplBase;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;

public class EnergyServer extends EnergyServiceImplBase{
	
	public static void main(String[] args) {
		
		System.out.println("Starting gRPC Energy Server");
		EnergyServer energyserver = new EnergyServer();

		int port = 50051;

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
	
	/*
	 * DEVICES - Unary RPC implementation
	 */
	
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
				
		DevicesOffRequest response = DevicesOffResponse.newBuilder().setDeviceOff(deviceOff).build();
				
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	/*
	 * LIGHTS - Unary RPC implementation
	 */

	@Override
	public void switchLightsOn(LightsSwitchRequest request, StreamObserver<LightsResponse> responseObserver) {
		
		System.out.println("Request received to turn lights on");
		
		int lightId = request.getLightId();
				
		LightsResponse.Builder response = LightsResponse.newBuilder();
		System.out.println("Turn on lights with ID: " + lightId);
				
		//for()
		response.setLightOn(1).setResponseMessage("Light ID:" + lightId + "on");
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
		}
	
	/*
	 * LIGHTS - Server Stream RPC implementation
	 */


	@Override
	public void lightIntensity(LightAdjustRequest request, StreamObserver<LightAdjustResponse> responseObserver) {
		
	}
	


	message LightAdjustRequest {
		int32 lightId = 1;
		string adjust = 2;
	}

	message LightAdjustResponse {
		string adjustMade = 1;
	}



	
}
