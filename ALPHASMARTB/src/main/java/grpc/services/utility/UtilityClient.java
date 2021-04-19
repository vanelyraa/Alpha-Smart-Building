package grpc.services.utility;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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
		public static void printStatement(){

			StreamObserver<PrintResponse> responseObserver = new StreamObserver<PrintResponse>() {

				@Override
				public void onNext(PrintResponse value) {
					System.out.println("receiving statement to print -> " + value.getStatement());
				}

				@Override
				public void onError(Throwable t) {

				}

				@Override
				public void onCompleted() {

				}

			};

			StreamObserver<PrintRequest> requestObserver = asyncStub.printStatement(responseObserver);
			try {

				requestObserver.onNext(PrintRequest.newBuilder().setStatement("Hello").build());
				requestObserver.onNext(PrintRequest.newBuilder().setStatement("Please").build());
				requestObserver.onNext(PrintRequest.newBuilder().setStatement("Print").build());
				requestObserver.onNext(PrintRequest.newBuilder().setStatement("This").build());
				requestObserver.onNext(PrintRequest.newBuilder().setStatement("Out").build());
				
				Thread.sleep(new Random().nextInt(1000) + 500);

			} catch (RuntimeException e) {
	            requestObserver.onError(e);
	            throw e;
	            } catch (InterruptedException e) {

	                e.printStackTrace();
	        }

			requestObserver.onCompleted();
		}
	}
}
