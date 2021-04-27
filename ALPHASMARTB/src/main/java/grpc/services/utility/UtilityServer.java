package grpc.services.utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import grpc.services.utility.UtilityServiceGrpc.UtilityServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class UtilityServer extends UtilityServiceImplBase{
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
	System.out.println("Starting gRPC Utilities Server");	
	
	try {
		int PORT = 50098;
		JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
		ServiceInfo serviceUtility = ServiceInfo.create("_http._tcp.local.", "utility", 50098, "path=index.html");
        jmdns.registerService(serviceUtility);
        UtilityServer utilityServer = new UtilityServer();
        Server server = ServerBuilder.forPort(PORT)
        	.addService(utilityServer)
            .build()
             .start();
        System.out.println("Utility server started, listening on " + PORT);
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
	public void switchDevices(DevicesRequest request, StreamObserver<DevicesResponse> responseObserver) {
		
		System.out.println("Receiving request to turn On devices!");

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
	
	@Override
	public void switchCameraOn(CameraRequest request, StreamObserver<CameraResponse> responseObserver) {
		
		System.out.println("Receiving request to turn On cameras!");

        boolean OnOffC = request.getCamera();
        if (OnOffC) {
        	System.out.println("Setting power to off!");
        } else {
        	System.out.println("Setting power to on!");
        }
        
        CameraResponse response = CameraResponse.newBuilder().setCamera(OnOffC).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

	}
	
	@Override
	public StreamObserver<PrinterRequest> printList(StreamObserver<PrinterResponse> responseObserver) {
		return new StreamObserver<PrinterRequest>() {
	        
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
	

