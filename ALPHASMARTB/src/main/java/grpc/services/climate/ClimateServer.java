package grpc.services.climate;

import java.io.IOException;
import java.util.ArrayList;
import grpc.services.climate.ClimateServiceGrpc.ClimateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateServer extends ClimateServiceImplBase {
	
public static void main(String[] args) {
		
		System.out.println("Starting gRPC Climate Server");
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
public void showHvacStatus(HvacStatusRequest request, StreamObserver<HvacResponse> responseObserver) {
	//loop all HVACs
	for (grpc.services.climate.Hvac hvac : Hvacs.getInstance()) {
        if (hvac.getHvacId() == request.getHvacId()) {
        	HvacResponse response = HvacResponse.newBuilder().setHvac(hvac).build();
        	responseObserver.onNext(response);
        	responseObserver.onCompleted();
            return;
        }
    }
	//super.showHvacStatus(request, responseObserver);
}

@Override
public void setHvacTemp(TempRequest request, StreamObserver<HvacResponse> responseObserver) {
	ArrayList<grpc.services.climate.Hvac> tempList = Hvacs.getInstance();
	int hvactemp = request.getTemperature();
    
	for (int i = 0; i < tempList.size(); i++) {
		grpc.services.climate.Hvac hvac = (grpc.services.climate.Hvac) tempList.get(i);
        Hvacs.hvacs.clear();
        	
        Hvacs.hvacs.add(grpc.services.climate.Hvac.newBuilder()
        			.setHvacId(hvac.getHvacId())
        			.setStatus(("On"))
        			.setTemperature(hvactemp)
        			.build());
        }
    
    for (grpc.services.climate.Hvac hvac : Hvacs.hvacs) {
    	HvacResponse response = HvacResponse.newBuilder().setHvac(hvac).build();
    	responseObserver.onNext(response);
    	responseObserver.onCompleted();
    	return;
    }
	//super.setHvacTemp(request, responseObserver);
}

@Override
public void checkCO(CoLevelRequest request, StreamObserver<ExtractionResponse> responseObserver) {
	for (grpc.services.climate.CoLevel coLevel : CoLevels.getInstance()) {
        if (coLevel.getBuilding() == request.getBuilding()) {
        	ExtractionResponse response = ExtractionResponse.newBuilder().setCoLevel(coLevel).build();
        	responseObserver.onNext(response);
        	responseObserver.onCompleted();
            return;
        }
	}
}
	//super.checkCO(request, responseObserver);
}




