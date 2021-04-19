package grpc.services.climate;

import java.io.IOException;

import grpc.services.climate.ClimateServiceGrpc.ClimateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateServer extends ClimateServiceImplBase  {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Server");
		ClimateServer climateserver = new ClimateServer();

		int port = 50052;

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


	@Override
	public void hvacOnOff(SwitchRequest request, StreamObserver<SwitchResponse> responseObserver) {
		
		System.out.println("Receiving request to turn on HVAC!");

        boolean OnOffH = request.getPower();
        if (OnOffH) {
        	System.out.println("Setting power to on!");
        }
        else {
        	System.out.println("Setting power to off!");
        }
        
        SwitchResponse response = SwitchResponse.newBuilder().setPower(OnOffH).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}

	@Override
	public void hvacTemperature(HvacRequest request, StreamObserver<HvacResponse> responseObserver) {

		int newTemp = request.getTemp();
		 System.out.println("Requested temperature change to -> " + newTemp + " C");
		 System.out.println("Temperature adjusted");
		 responseObserver.onNext(HvacResponse.newBuilder().setTemp(newTemp).build());
		 
		 responseObserver.onCompleted();

	}

	@Override
	public void checkCO(CoLevelRequest request, StreamObserver<ExtractionResponse> responseObserver) {

System.out.println("Checking CO levels in the office!");
		
		int CoLv = request.getLevel();
       
        if (CoLv > 40) {
        	System.out.println("Turn extraction on!");
        }
        else {
        	System.out.println("Turn extraction off!");
        }
        
        ExtractionResponse response = ExtractionResponse.newBuilder().setLevel(CoLv).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}

}
