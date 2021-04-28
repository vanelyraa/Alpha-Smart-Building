package grpc.services.utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

//CLIENT IMPLEMENTATION
public class UtilityClient {
		
	private static UtilityServiceGrpc.UtilityServiceBlockingStub ublockingStub;
	private static UtilityServiceGrpc.UtilityServiceStub uasyncStub;
		
	//Add listener for discovery
	public static class Listener implements ServiceListener {
		//service resolution 
        @Override
        public void serviceAdded(ServiceEvent serviceEvent) {
            System.out.println("Service added: " + serviceEvent.getInfo());
        }
        
        //removed service
        @Override
        public void serviceRemoved(ServiceEvent serviceEvent) {
            System.out.println("Service removed: " + serviceEvent.getInfo());
        }

        //service resolved.
        @Override
        public void serviceResolved(ServiceEvent serviceEvent) {
            System.out.println("Service resolved: " + serviceEvent.getInfo());
            ServiceInfo info = serviceEvent.getInfo();
            final int Port = serviceEvent.getInfo().getPort();
            String address = info.getHostAddresses()[0];           
        }
    }
		
	public static void main(String[] args) throws Exception {
		
		//GRPC channels
		ManagedChannel utilitychannel = ManagedChannelBuilder.forAddress("localhost", 50098).usePlaintext().build();

		//stubs -- generate from proto
		ublockingStub = UtilityServiceGrpc.newBlockingStub(utilitychannel);
		uasyncStub = UtilityServiceGrpc.newStub(utilitychannel);
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Add a service listener
			jmdns.addServiceListener("_http._tcp.local.", new Listener());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		switchDevices();
		switchCameraOn();
		printList();
	}
	
	
	/**
	 * GRPC services
	 */
	
	//Switch devices	
	public static void switchDevices(){
		
		DevicesRequest request = DevicesRequest.newBuilder().setDevices(false).build();

		//notification of method invocation
		DevicesResponse response = ublockingStub.switchDevices(request);
		if (response.getDevices()) {
			System.out.println("Devices off!");
		}
		else {
			System.out.println("Devices on!");
		}
	}
	
	//Switch cameras
	public static void switchCameraOn(){
		
		CameraRequest request = CameraRequest.newBuilder().setCamera(false).build();

		//if true, cameras off otherwise, on
		CameraResponse response = ublockingStub.switchCameraOn(request);
		if (response.getCamera()) {
			System.out.println("Camera off!");
		}
		else {
			System.out.println("Camera on!");
		}
	}
	
	//Print visit list
	public static void printList(){

		StreamObserver<PrinterResponse> responseObserver = new StreamObserver<PrinterResponse>() {

			@Override
			public void onNext(PrinterResponse value) {
				System.out.println("Printing visit list: " + value.getPList());
			}

			@Override
			public void onError(Throwable t) {
			}

			@Override
			public void onCompleted() {
			}

		};

		StreamObserver<PrinterRequest> requestObserver = uasyncStub.printList(responseObserver);
			try {
				//visitors inputs
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Walter Knutz").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Maria Stunnten").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Rodrigo Salez").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Jessica Klint").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Jeniffer Kellei").build());
				
				Thread.sleep(2000);
			} catch (RuntimeException e) {
	            requestObserver.onError(e);
	            throw e;	            	
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	        }
			requestObserver.onCompleted();
	}
}


