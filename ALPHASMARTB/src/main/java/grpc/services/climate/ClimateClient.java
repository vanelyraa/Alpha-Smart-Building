package grpc.services.climate;


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

public class ClimateClient {
	
	private static ClimateServiceGrpc.ClimateServiceBlockingStub cblockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub casyncStub;
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		ManagedChannel climatechannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		cblockingStub = ClimateServiceGrpc.newBlockingStub(climatechannel);
		casyncStub = ClimateServiceGrpc.newStub(climatechannel);
		
		
		HvacOnOff();
		HvacTemperature();
		checkCO();
		
	}
	
	public static void HvacOnOff(){
		
		SwitchRequest request = SwitchRequest.newBuilder().setPower(false).build();

		SwitchResponse response = cblockingStub.hvacOnOff(request);

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

		casyncStub.hvacTemperature(request, responseObserver);


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkCO(){
		
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(25).build();

		ExtractionResponse response = cblockingStub.checkCO(request);

		if (response.getLevel() > 40) {
			System.out.println("Co level is: " + response.getLevel() + "now");
			System.out.println("High level of CO, extractor is on!");
		}
		else {
			System.out.println("Extractor off!");
		}
	}
}
