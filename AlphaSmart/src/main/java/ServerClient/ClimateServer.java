package ServerClient;

import java.io.IOException;import grpc.services.climate.ClimateServiceGrpc;
import grpc.services.climate.CoLevelRequest;
import grpc.services.climate.ExtractionResponse;
import grpc.services.climate.HvacResponse;
import grpc.services.climate.HvacStatusRequest;
import grpc.services.climate.TempRequest;
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
	
	//HVAC
	@Override
	public void showHvacStatus(HvacStatusRequest request, StreamObserver<HvacResponse> responseObserver) {
		for (Hvac hvac : HvacData.getInstance()) {
            if (Hvac.getHvacId() == request.getHvacId()) {
            	HvacResponse response = HvacResponse.newBuilder().setHvac(hvac).build();
            	responseObserver.onNext(response);
            	responseObserver.onCompleted();
                return;
            }
        }
	}
	
	//HVAC TEMPERATURE
	@Override
	public void setHvacTemp(TempRequest request, StreamObserver<HvacResponse> responseObserver) {
		ArrayList<Hvac> hvacList = HvacData.getInstance();
        for (int i = 0; i < hvacList.size(); i++) {
            if (hvacList.get(i).getHeaterId() == request.getHvacId()) {
            	Hvac hvac_rec = (Hvac) heatList.get(i);
            	HvacData.heat.clear();
            	HvacData.heat.add(Hvac.newBuilder().setHvacId(hvac_rec.getHvacId()).setStatus((hvac_rec.getHvacId())).setTemperature(hvac_rec.getTemperature()).build());
            }
        }
        for (Hvac hvac : HvacData.heat) {
        	HvacResponse response = HvacResponse.newBuilder().setHeater(hvac).build();
        	responseObserver.onNext(response);
        	responseObserver.onCompleted();
            
	}

	//CHECK CO LEVEL
	@Override
	public void checkCO(CoLevelRequest request, StreamObserver<ExtractionResponse> responseObserver) {
		
		for (CoLevel coLevel : CoLevelData.getInstance()) {
            if (CoLevel.getBuilding() == request.getBuilding()) {
            	ExtractionResponse response = ExtractionResponse.newBuilder().setColevel(coLevel).build();
            	responseObserver.onNext(response);
            	responseObserver.onCompleted();
                return;
            }
        }
	}




