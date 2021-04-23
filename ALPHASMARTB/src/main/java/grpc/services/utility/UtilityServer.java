package grpc.services.utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import grpc.services.light.LightServer;
import grpc.services.utility.UtilityServiceGrpc.UtilityServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class UtilityServer extends UtilityServiceImplBase{
	
	private static final Logger logger = Logger.getLogger(LightServer.class.getName());
	public static int utilityPort;
	
	private static class Listener implements ServiceListener {
		 
        
            @Override
        	public void serviceAdded(ServiceEvent event) {
        		System.out.println("Service added: " + event.getInfo());
        	}


        	@Override
        	public void serviceRemoved(ServiceEvent event) {
        		System.out.println("Service removed: " + event.getInfo());
        		
        	}


        	@Override
        	public void serviceResolved(ServiceEvent event) {
        		System.out.println("Service resolved: " + event.getInfo());
                System.out.println("Get Name: " + event.getName()+" PORT: "+event.getInfo().getPort());
      
        try {
        	utilityPort = event.getInfo().getPort();
			startGRPC(event.getInfo().getPort());
   		} 
   		catch (IOException e) {
			e.printStackTrace();
		} 
   		catch (InterruptedException e) {
			e.printStackTrace();
		}
        }
	}

        
        
        
	public static void main(String[] args) throws IOException, InterruptedException {
		 startDiscovery();
	}
	
	
	public static void startDiscovery() throws IOException, InterruptedException {	
		System.out.println("Starting utility gRPC Server");
		
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener("_http._tcp.local.", new Listener());
			System.out.println("Listener service");
		    // Wait a bit
		    Thread.sleep(10000);
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	public int getUtilityPort() {
		return utilityPort;
	}

	public void setUtilityPort(int utilityPort) {
		UtilityServer.utilityPort = utilityPort;
	}
	
	public static void startGRPC(int portNumber) throws IOException, InterruptedException {
		UtilityServer utilityServer = new UtilityServer();
		    
		Server server = ServerBuilder.forPort(portNumber).addService(utilityServer).build().start();
		logger.info("LightingServer started, listening on " + portNumber);		     
		server.awaitTermination();
	 }


	@Override
	public void switchDevices(DevicesRequest request, StreamObserver<DevicesResponse> responseObserver) {
		
		System.out.println("Receiving request to turn off devices!");

        boolean OnOffD = request.getDevices();
        if (OnOffD) {
        	System.out.println("Setting power to off!");
        }
        else {
        	System.out.println("Setting power to on!");
        }
        
        DevicesResponse response = DevicesResponse.newBuilder().setDevices(OnOffD).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}
	
	@Override
	public void switchCameraOn(CameraRequest request, StreamObserver<CameraResponse> responseObserver) {
		
		System.out.println("Receiving request to turn cameras on!");

        boolean OnOffC = request.getCamera();
        if (OnOffC) {
        	System.out.println("Setting power to on!");
        }
        else {
        	System.out.println("Setting power to off!");
        }
        
        CameraResponse response = CameraResponse.newBuilder().setCamera(OnOffC).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}
	
	@Override
	public StreamObserver<PrinterRequest> printList(StreamObserver<PrinterResponse> responseObserver) {
		return new StreamObserver<PrinterRequest>() {
	        
			public void onNext(PrinterRequest request) {
	            StringBuilder pList = new StringBuilder(); 
	  
	            pList.append(request.getPList());
	            System.out.println(pList);
	        
	            PrinterResponse toPrint = PrinterResponse.newBuilder().setPList(pList.toString()).build();
	      
	            responseObserver.onNext(toPrint);
			}

	        public void onError(Throwable t) {
	        	System.out.println("Error on printing service");
	        }
	        
	        public void onCompleted() {
	          responseObserver.onCompleted();
	        }
		};
	}


	
}
	

