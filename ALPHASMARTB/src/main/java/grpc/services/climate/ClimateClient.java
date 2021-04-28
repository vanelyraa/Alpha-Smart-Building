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

//CLIENT IMPLEMENTATION
public class ClimateClient {
	
	private static ClimateServiceGrpc.ClimateServiceBlockingStub cblockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub casyncStub;
	public static int randomCo = (int)(Math.random() * 100 + 1);
	
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
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		//GRPC channels
		ManagedChannel climatechannel = ManagedChannelBuilder.forAddress("localhost", 50099).usePlaintext().build();

		//stubs -- generate from proto
		cblockingStub = ClimateServiceGrpc.newBlockingStub(climatechannel);
		casyncStub = ClimateServiceGrpc.newStub(climatechannel);
		
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
		
		hvacOnOff();
		HvacTemperature();
		checkCO();
		
	}
	
	/**
	 * GRPC services
	 */

	//Switch HVAC
	public static void hvacOnOff(){
		
		SwitchRequest request = SwitchRequest.newBuilder().setPower(false).build();

		SwitchResponse response = cblockingStub.hvacOnOff(request);
		//if true, HVAC off otherwise, on
		if (response.getPower()) {
			System.out.println("HVAC off!");
		} else {
			System.out.println("HVAC on!");
		}
	}

	//Control temperature
	public static void HvacTemperature(){
		
		HvacRequest request = HvacRequest.newBuilder().setTemp(20).build();
		int newTemp = request.getTemp();
		
		//notification of method invocation
		System.out.println("Requesting temperature change to " + request.getTemp() + " °C");

		StreamObserver<HvacResponse> responseObserver = new StreamObserver<HvacResponse>() {
			
			//minimum and max temperature check
			@Override
			public void onNext(HvacResponse value) {
				if(newTemp > 35 || newTemp < 15 ) {//start if
					System.out.println("Select temperature between 15°C and 35°C ");
				} else {
				System.out.println("Changing temperature to: " + value.getTemp() + " °C");
				}
			}
	
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
	
			@Override
			public void onCompleted() {
				System.out.println("Room reached the selected temperature: "+request.getTemp()+"°C");
			}
		};

		casyncStub.hvacTemperature(request, responseObserver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Check CO level in the room
	public static void checkCO(){
		
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(randomCo).build();
		
		//if CO level is over 40, extractors will be turned on
		ExtractionResponse response = cblockingStub.checkCO(request);
		if (response.getLevel() > 40) {
			System.out.println("Co level is: " + response.getLevel() + " now");
			System.out.println("High level of CO, extractor is on!");
		}
		else {
			System.out.println("Co level is: " + response.getLevel() + " now");
			System.out.println("CO level OK!");
		}
	}
}
