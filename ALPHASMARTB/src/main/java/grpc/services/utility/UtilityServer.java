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
		// TODO Auto-generated method stub
		System.out.println("Starting gRPC Server");
		
		try {
			int port = 50051;
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	        ServiceInfo serviceInfo = ServiceInfo.create("_utility._tcp.local.", "utility", port, "Utility server: devices, camera and printer control");
	        jmdns.registerService(serviceInfo);
		
		UtilityServer utilityserver = new UtilityServer();
		Server server = ServerBuilder.forPort(port)
				.addService(utilityserver)
				.build()
				.start();

			System.out.println("Server started with Port:" + port);
		    server.awaitTermination();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

	}//main

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
	

