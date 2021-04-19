package grpc.services.climate;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClimateClient {
	
	private static ClimateServiceGrpc.ClimateServiceBlockingStub blockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub asyncStub;

	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

		blockingStub = ClimateServiceGrpc.newBlockingStub(channel);
		asyncStub = ClimateServiceGrpc.newStub(channel);
		
		HvacOnOff();
		HvacTemperature();
		checkCO();
		
	}
	
	public static void HvacOnOff(){
		
		SwitchRequest request = SwitchRequest.newBuilder().setPower(false).build();

		SwitchResponse response = blockingStub.hvacOnOff(request);

		if (response.getPower()) {
			System.out.println("HVAC is on!");
		}
		else {
			System.out.println("HVAC is off!");
		}
	}

	public static void HvacTemperature(){
		HvacRequest request = HvacRequest.newBuilder().setTemp(20).build();
		System.out.println("Set room temperature to " + request + " C");

		StreamObserver<HvacResponse> responseObserver = new StreamObserver<HvacResponse>() {

			@Override
			public void onNext(HvacResponse tempNew) {
				System.out.println("Temperature changed: " + tempNew + " C");
			}
	
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
	
			@Override
			public void onCompleted() {
				System.out.println("completed ");
			}

		};

		asyncStub.hvacTemperature(request, responseObserver);


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkCO(){
		
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(25).build();

		ExtractionResponse response = blockingStub.checkCO(request);

		if (response.getLevel() > 40) {
			System.out.println("Co level is: " + response.getLevel() + "now");
			System.out.println("High level of CO, extractor is on!");
		}
		else {
			System.out.println("Extractor off!");
		}
	}
}
