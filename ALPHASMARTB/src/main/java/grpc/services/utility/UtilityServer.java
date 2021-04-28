package grpc.services.utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import grpc.services.utility.UtilityServiceGrpc.UtilityServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

//SERVER SIDE IMPLEMENTATION
public class UtilityServer extends UtilityServiceImplBase{
	
	private static final Logger logger = Logger.getLogger(UtilityServer.class.getName());
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		System.out.println("Starting gRPC Utilities Server");	
		
		try {
			//Defining the port
			int PORT = 50098;
			
			//Creating a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Adding a service listener
			ServiceInfo serviceUtility = ServiceInfo.create("_http._tcp.local.", "utility", 50098, "path=index.html");
	        jmdns.registerService(serviceUtility);
	        
	        //getting a reference to the server
	        UtilityServer utilityServer = new UtilityServer();
	        Server server = ServerBuilder.forPort(PORT)
	        	.addService(utilityServer)
	            .build()
	             .start();
	        logger.info("Utility server started, listening on " + PORT);
	        server.awaitTermination();        
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
	        e.printStackTrace();
		} catch (IOException e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    }	
	}
	
	/**
	 * GRPC services
	 */
	
	//Switch devices
	@Override
	public void switchDevices(DevicesRequest request, StreamObserver<DevicesResponse> responseObserver) {
		
		//notification of method invocation
		System.out.println("Receiving request to turn On/Off devices!");

		//if true, devices off otherwise, on
        boolean OnOffD = request.getDevices();
        if (OnOffD) {
        	System.out.println("Setting devices off!");
        } else {
        	System.out.println("Setting devices on!");
        }
        
        DevicesResponse response = DevicesResponse.newBuilder().setDevices(OnOffD).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
	//Switch cameras
	@Override
	public void switchCameraOn(CameraRequest request, StreamObserver<CameraResponse> responseObserver) {
		
		//notification of method invocation
		System.out.println("Receiving request to turn On/Off cameras!");

		//if true, cameras off otherwise, on
        boolean OnOffC = request.getCamera();
        if (OnOffC) {
        	System.out.println("Setting cameras off!");
        } else {
        	System.out.println("Setting cameras on!");
        }
        
        CameraResponse response = CameraResponse.newBuilder().setCamera(OnOffC).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}
	
	//Print visit list
	@Override
	public StreamObserver<PrinterRequest> printList(StreamObserver<PrinterResponse> responseObserver) {
		return new StreamObserver<PrinterRequest>() {
	        
			//Stringbuilder to hold all visitors names added
			public void onNext(PrinterRequest request) {
	            StringBuilder sb = new StringBuilder(request.getPList()); 
	            PrinterResponse toPrint = PrinterResponse.newBuilder().setPList(sb.toString()).build();	           
	            responseObserver.onNext(toPrint);
			}

	        public void onError(Throwable t) {
	        	System.out.println("Printing server failed");
	        }
	        
	        public void onCompleted() {
	          responseObserver.onCompleted();
	        }
		};
	}	
}
	

