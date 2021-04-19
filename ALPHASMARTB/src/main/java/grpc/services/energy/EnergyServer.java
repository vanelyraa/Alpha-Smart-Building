package grpc.services.energy;

import java.io.IOException;
import java.util.ArrayList;

import grpc.services.energy.EnergyServiceGrpc.EnergyServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class EnergyServer extends EnergyServiceImplBase {
	
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
ArrayList<grpc.services.energy.Lights> light_list = LightsData.getInstance();
		
		for(int i=0; i < light_list.size(); i++) {
			grpc.services.energy.Lights light = (grpc.services.energy.Lights) light_list.get(i);
			LightsData.lightsdata.clear();
			
			LightsData.lightsdata.add(grpc.services.energy.Lights.newBuilder()
					.setLightId(light.getLightId())
					.setLightStatus("ON")
					.setAdjust(light.getAdjust())//turning off the heater 
					.build());
		}
		
		//need to loop through all fans in array
		for(grpc.services.energy.Lights light : LightsData.lightsdata) {
			LightsResponse response = LightsResponse.newBuilder().setLight(light).build();
			
			//this is the next response to send
			responseObserver.onNext(response);
			//send the message to let server know we are finished sending
			responseObserver.onCompleted();
			return;
		}

	}

	@Override
	public StreamObserver<LightAdjustRequest> lightIntensity(StreamObserver<LightsResponse> responseObserver) {
		return new StreamObserver<LightAdjustRequest>() {
			//initializing variable to handle input
			int adjust = 0;
			
			public void onNext(LightAdjustRequest lsr) {
				// set the variable equal to the value of the incoming request
				adjust = lsr.getAdjust();
				System.out.println("Request recieved to adjust lights to: " + adjust);
			}
			
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			// build the response using the setting variable
			public void onCompleted() {
				System.out.println("Lights adjust");
				responseObserver.onNext(LightsResponse.newBuilder().setLights(adjust).build());
				
			}
		};
	}
	
	

	
}
