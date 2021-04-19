package grpc.services.utility;

import java.util.Random;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UtilityClient {
	
	private static UtilityServiceGrpc.UtilityServiceBlockingStub blockingStub;
	private static UtilityServiceGrpc.UtilityServiceStub asyncStub;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		blockingStub = UtilityServiceGrpc.newBlockingStub(channel);
		asyncStub = UtilityServiceGrpc.newStub(channel);

		switchDevices();
		switchCameraOn();
		printList();
	}
	
	public static void switchDevices(){
		
		DevicesRequest request = DevicesRequest.newBuilder().setDevices(false).build();

		DevicesResponse response = blockingStub.switchDevices(request);

		if (response.getDevices()) {
			System.out.println("Devices off!");
		}
		else {
			System.out.println("Devices on!");
		}
	}
		
	public static void switchCameraOn(){
		
		CameraRequest request = CameraRequest.newBuilder().setCamera(false).build();

		CameraResponse response = blockingStub.switchCameraOn(request);

		if (response.getCamera()) {
			System.out.println("Motion detected, camera on!");
		}
		else {
			System.out.println("Camera off!");
		}
	}
	
	public static void printList(){

		StreamObserver<PrinterResponse> responseObserver = new StreamObserver<PrinterResponse>() {

			@Override
			public void onNext(PrinterResponse value) {
				System.out.println("Printing following: " + value.getPList());
			}

			@Override
			public void onError(Throwable t) {
			}

			@Override
			public void onCompleted() {
			}

		};

		StreamObserver<PrinterRequest> requestObserver = asyncStub.printList(responseObserver);
			try {
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Print").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("These").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Files").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Please").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Thanks").build());
				
				Thread.sleep(new Random().nextInt(500) + 1000);

			} catch (RuntimeException e) {
	            requestObserver.onError(e);
	            	throw e;
	            	
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	        }
			requestObserver.onCompleted();
	}
}

