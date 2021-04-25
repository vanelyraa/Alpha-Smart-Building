package grpc.services.climate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import grpc.services.climate.ClimateServiceGrpc.ClimateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateServer extends ClimateServiceImplBase  {
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
	System.out.println("Starting gRPC Climate Server");
	
	// Create & Register utilities service with jmDNS
	try {
		int PORT = 50051;
		JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_climate._tcp.local.", "climate", PORT, "Climate server");
        jmdns.registerService(serviceInfo);
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
        	responseObserver.onNext(ExtractionResponse.newBuilder().setLevel(CoLv).build());
        	System.out.println("Turn extraction on!");
        	
        }
        else {
        	responseObserver.onNext(ExtractionResponse.newBuilder().setLevel(CoLv).build());
        	System.out.println("Turn extraction off!");
        }
        
        //ExtractionResponse response = ExtractionResponse.newBuilder().setLevel(CoLv).build();
        //System.out.println("this is response   "+response);
        //responseObserver.onNext(response);
        responseObserver.onCompleted();

	}

}
