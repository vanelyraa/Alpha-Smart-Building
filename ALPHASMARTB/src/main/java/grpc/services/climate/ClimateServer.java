package grpc.services.climate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import grpc.services.climate.ClimateServiceGrpc.ClimateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateServer extends ClimateServiceImplBase{
	
public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Starting gRPC Climate Server");
		
		
		try {
			int PORT = 50099;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			ServiceInfo serviceClimate = ServiceInfo.create("_http._tcp.local.", "climate", 50099, "path=index.html");
	        jmdns.registerService(serviceClimate);
	        ClimateServer climateServer = new ClimateServer();
	        Server server = ServerBuilder.forPort(PORT)
                    .addService(climateServer)
                    .build()
                    .start();
            System.out.println("Climate server started, listening on " + PORT);
            server.awaitTermination();
            
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
	}


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
		
		System.out.println("Receiving request to change temperature to: "+ newTemp +"°C");
		try {
			HvacResponse response = HvacResponse.newBuilder().setTemp(newTemp+3).build();
			HvacResponse response1 = HvacResponse.newBuilder().setTemp(newTemp-1).build();
			HvacResponse response2 = HvacResponse.newBuilder().setTemp(newTemp).build();
			
			// delaying the response to simulate the system heating to the desired temperature
			try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			 responseObserver.onNext(response);
			 try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			 responseObserver.onNext(response1);
			 try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			 responseObserver.onNext(response2);
		} catch (Error e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

		responseObserver.onCompleted();
	}
	
	@Override
	public void checkCO(CoLevelRequest request, StreamObserver<ExtractionResponse> responseObserver) {

		System.out.println("Checking CO levels in the office!");
		
		int CoLv = request.getLevel();
       
        if (CoLv > 40) {
        	responseObserver.onNext(ExtractionResponse.newBuilder().setLevel(CoLv).build());
        	System.out.println("CO level is: " + CoLv);
        		System.out.println("High level of CO, extractor is on!");
		}      
        else {
        	responseObserver.onNext(ExtractionResponse.newBuilder().setLevel(CoLv).build());
        	System.out.println("CO level OK!");
        }
        
        responseObserver.onCompleted();

	}


	

}
