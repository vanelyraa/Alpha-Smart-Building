package grpc.services.climate;

import java.io.IOException;
import java.net.InetAddress;
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
	
	public static int climatePort;
	private static final Logger logger = Logger.getLogger(ClimateServer.class.getName());
	
	private static class Listener implements ServiceListener {
		 
        public void serviceAdded(ServiceEvent event) {
            System.out.println("Service added: " + event.getInfo());
        }

        
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }

        
        public void serviceResolved(ServiceEvent event) {
        	System.out.println("Service resolved: " + event.getInfo());
            System.out.println("Get Name: " + event.getName()+" PORT: "+event.getInfo().getPort());
            
            //Start GRPC server with discovered device
            if(event.getName().equals("Climate")) {
            	System.out.println("Found climate port: " + event.getInfo().getPort());
	       		try {
	       			climatePort = event.getInfo().getPort();
					startGRPC(event.getInfo().getPort());
	       		} 
	       		catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	       		catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
          

        }
    }
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		startDiscovery();
	}
		
	public static void startDiscovery() throws IOException, InterruptedException {
		System.out.println("Starting climate gRPC Server");
		
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener("_http._tcp.local.", new Listener());
		    System.out.println("Listener working");
		    
		    Thread.sleep(20000);
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

	}//main

	public int getClimatePort() {
		return climatePort;
	}

	public void settClimatePort(int climatePort) {
		ClimateServer.climatePort = climatePort;
	}
	
	public static void startGRPC(int portNumber) throws IOException, InterruptedException {
		ClimateServer climateServer = new ClimateServer();
		    
		Server server = ServerBuilder.forPort(portNumber).addService(climateServer).build().start();
		logger.info("ClimateServer started, listening on " + portNumber);		     
		server.awaitTermination();
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
