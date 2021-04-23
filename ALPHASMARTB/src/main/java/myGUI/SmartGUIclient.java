package myGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import grpc.services.climate.ClimateServer;
import grpc.services.climate.ClimateServiceGrpc;
import grpc.services.climate.CoLevelRequest;
import grpc.services.climate.ExtractionResponse;
import grpc.services.climate.HvacRequest;
import grpc.services.climate.HvacResponse;
import grpc.services.climate.SwitchRequest;
import grpc.services.climate.SwitchResponse;
import grpc.services.light.Empty;
import grpc.services.light.IntensityRequest;
import grpc.services.light.IntensityResponse;
import grpc.services.light.LightServer;
import grpc.services.light.LightServiceGrpc;
import grpc.services.light.LightingResponse;
import grpc.services.light.LightsRequest;
import grpc.services.light.LightsResponse;
import grpc.services.utility.CameraRequest;
import grpc.services.utility.CameraResponse;
import grpc.services.utility.DevicesRequest;
import grpc.services.utility.DevicesResponse;
import grpc.services.utility.PrinterRequest;
import grpc.services.utility.PrinterResponse;
import grpc.services.utility.UtilityServer;
import grpc.services.utility.UtilityServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jmDNS.ServiceRegistration;

import java.awt.GridLayout;
import java.awt.Label;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JTextField;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.awt.Canvas;
import java.awt.List;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JSlider;
import java.awt.Checkbox;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SmartGUIclient{

	private JFrame frame;
	int climatePort = 50051;
	int utilityPort = 50052;
	int lightPort = 50053;
	private static String host = "localhost";
	private static ClimateServiceGrpc.ClimateServiceBlockingStub cblockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub casyncStub;
	private static ClimateServiceGrpc.ClimateServiceFutureStub cfutureStub;
	private static LightServiceGrpc.LightServiceBlockingStub lblockingStub;
	private static LightServiceGrpc.LightServiceStub lasyncStub;
	private static LightServiceGrpc.LightServiceFutureStub lfutureStub;
	private static UtilityServiceGrpc.UtilityServiceBlockingStub ublockingStub;
	private static UtilityServiceGrpc.UtilityServiceStub uasyncStub;
	private static UtilityServiceGrpc.UtilityServiceFutureStub ufutureStub;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() { //check line
			public void run() {//check line
		
				try {
			SmartGUIclient window = new SmartGUIclient();
			window.frame.setVisible(true);
				 } catch (Exception e) {
			            e.printStackTrace();
			        }
				}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public SmartGUIclient() throws InterruptedException, IOException {
		initialize();
		
	ServiceRegistration reg = new ServiceRegistration();
	//Start Item Registry, GRPC servers and channels then unregister
	reg.jmndsRegister(climatePort, utilityPort, lightPort);
	startGRPCServers();
	channels();
	reg.unRegister();
	loadInitialItems();
}

public void startGRPCServers() throws IOException, InterruptedException {
	ClimateServer.startDiscovery();
	LightServer.startDiscovery();
	UtilityServer.startDiscovery();

}
public void loadInitialItems() throws IOException, InterruptedException {
	lighting();
	
}

public void channels() {
	
	System.out.println("CHANNELS STARTING");
	ManagedChannel climateChannel = ManagedChannelBuilder.forAddress("localhost",climatePort).usePlaintext().build();
	ManagedChannel lightChannel = ManagedChannelBuilder.forAddress("localhost", lightPort).usePlaintext().build();
	ManagedChannel utilitiesChannel = ManagedChannelBuilder.forAddress("localhost", utilityPort).usePlaintext().build();

	cblockingStub = ClimateServiceGrpc.newBlockingStub(climateChannel);
	casyncStub = ClimateServiceGrpc.newStub(climateChannel);
	cfutureStub = ClimateServiceGrpc.newFutureStub(climateChannel);
	
	lblockingStub = LightServiceGrpc.newBlockingStub(lightChannel);
	lasyncStub = LightServiceGrpc.newStub(lightChannel);
	lfutureStub = LightServiceGrpc.newFutureStub(lightChannel);
	
	ublockingStub = UtilityServiceGrpc.newBlockingStub(utilitiesChannel);
	uasyncStub = UtilityServiceGrpc.newStub(utilitiesChannel);
	ufutureStub = UtilityServiceGrpc.newFutureStub(utilitiesChannel);
	
}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Alpha Smart dashboard");
		frame.setBounds(100, 100, 479, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Heading 
		JButton btnNewButton = new JButton("Climate");
		btnNewButton.setBounds(130, 74, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lighting");
		btnNewButton_1.setBounds(247, 74, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Utilities");
		btnNewButton_2.setBounds(364, 74, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Home");
		btnNewButton_3.setBounds(10, 74, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 125, 443, 202);
		frame.getContentPane().add(layeredPane_1);
		layeredPane_1.setLayout(new CardLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Alpha Smart Building Solutions");
		lblNewLabel_2.setFont(new Font("Papyrus", Font.BOLD, 27));
		lblNewLabel_2.setBounds(20, 11, 421, 52);
		frame.getContentPane().add(lblNewLabel_2);
		
		//Home panel
		JPanel home = new JPanel();
		layeredPane_1.add(home, "name_11653671090600");
		home.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Alpha Smart Dashboard!");
		lblNewLabel.setBounds(101, 32, 239, 16);
		home.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Click on the buttons above to go to the desired dashboard.");
		lblNewJgoodiesLabel.setBounds(52, 73, 337, 29);
		home.add(lblNewJgoodiesLabel);
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Climate panel
		JPanel climate = new JPanel();
		layeredPane_1.add(climate, "name_11658509311800");
		climate.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("HVAC");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(22, 51, 153, 14);
		climate.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Climate control panel");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(117, 0, 204, 25);
		climate.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Temperature");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(263, 51, 103, 14);
		climate.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("CO level");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(269, 125, 65, 14);
		climate.add(lblNewLabel_1_2_1_1);
		
		JButton btnNewButton_5 = new JButton("Check");
		btnNewButton_5.setBounds(263, 151, 71, 23);
		climate.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("+");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(+1);
		    	changeTemperature(1);
			}
		});
		btnNewButton_6.setBounds(263, 80, 41, 23);
		climate.add(btnNewButton_6);
		
		JButton btnNewButton_6_1 = new JButton("-");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(-1);
		    	changeTemperature(-1);
			}
		});
		btnNewButton_6_1.setBounds(304, 80, 41, 23);
		climate.add(btnNewButton_6_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("On");
		tglbtnNewToggleButton.setSelected(true);
		tglbtnNewToggleButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnNewToggleButton.isSelected()){
					tglbtnNewToggleButton.setText("On");
					 onOff(true);

	            } else {
	            	tglbtnNewToggleButton.setText("Off");
	            	 onOff(false);
	            }
			}
		});
		tglbtnNewToggleButton.setBounds(22, 76, 121, 23);
		climate.add(tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1.setBounds(22, 151, 121, 23);
		climate.add(tglbtnNewToggleButton_1);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Extractor");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3.setBounds(22, 125, 153, 14);
		climate.add(lblNewLabel_1_2_3);
		
		//Light panel
		JPanel light = new JPanel();
		layeredPane_1.add(light, "name_11670634583400");
		light.setLayout(null);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Lights control panel");
		lblNewLabel_3_1_1.setBounds(132, 5, 178, 22);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		light.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Choose light:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2.setBounds(19, 66, 102, 14);
		light.add(lblNewLabel_1_2_2);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(132, 65, 30, 20);
		light.add(spinner_1);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Brightness");
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2_1.setBounds(19, 111, 102, 14);
		light.add(lblNewLabel_1_2_2_1);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("OFF");
		rdbtnNewRadioButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton_4.setBounds(132, 156, 109, 23);
		light.add(rdbtnNewRadioButton_4);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Swicth");
		lblNewLabel_1_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2_1_1.setBounds(19, 158, 102, 14);
		light.add(lblNewLabel_1_2_2_1_1);
		
		JButton btnNewButton_7 = new JButton("+");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(+1);
		    	changeBrightness(1);
			}
		});
		btnNewButton_7.setBounds(109, 109, 41, 23);
		light.add(btnNewButton_7);
		
		JButton btnNewButton_7_1 = new JButton("-");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(-1);
		    	changeBrightness(-1);
			}
		});
		btnNewButton_7_1.setBounds(149, 109, 41, 23);
		light.add(btnNewButton_7_1);
		
		//Utility panel
		JPanel utility = new JPanel();
		layeredPane_1.add(utility, "name_11672794027300");
		utility.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Cameras");
		rdbtnNewRadioButton.setBounds(20, 77, 77, 25);
		utility.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Devices");
		rdbtnNewRadioButton_1.setBounds(20, 105, 69, 25);
		utility.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Switch OFF utilities");
		lblNewLabel_1.setBounds(10, 49, 153, 14);
		utility.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Utilities control panel");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(130, 0, 204, 25);
		utility.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Print visitor`s list");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(268, 49, 124, 14);
		utility.add(lblNewLabel_1_1);
		
		JButton btnNewButton_4 = new JButton("Print");
		btnNewButton_4.setBounds(280, 89, 89, 23);
		utility.add(btnNewButton_4);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//HVAC SERVICES
public static void LightOn(){
		
		SwitchRequest request = SwitchRequest.newBuilder().setPower(true).build();

		SwitchResponse response = cblockingStub.hvacOnOff(request);

		if (response.getPower()) {
			System.out.println("HVAC is on!");
		}
		else {
			System.out.println("HVAC is off!");
		}
	}

public static void LightOff(){
	
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
	
	
	//LIGHTS SERVICES
	public static void lighting(){
		Empty emp = Empty.newBuilder().build();
		
		System.out.println("Lights");
		LightingResponse response;
		//Error Handling
		try {
			 response = lblockingStub.lighting(emp);
			 String intens = String.valueOf(response.getIntensity());
				System.out.println("LightID: " + response.getLightId());
				System.out.println("Light status: " + response.getStatus());
				System.out.println("Brightness: " + intens);
		}catch(io.grpc.StatusRuntimeException e) {
			System.out.println("RPC lighiting failed:"+ e.getStatus());
			return;
		}	
	}

	public static void LightsOnOff(){
		LightsRequest request = LightsRequest.newBuilder().setSwitch(false).build();
		
		// check the response
		LightsResponse response = lblockingStub.lightsOnOff(request);
		
		// print appropriate message depending on response
		if (response.getSwitch()) {
			System.out.println("Lights turned on!");
		}
		else {
			System.out.println("Lights off!");
		}
	}
	
	public static void lightIntensity(){
		StreamObserver<IntensityResponse> responseObserver = new StreamObserver<IntensityResponse>() {

			@Override
			public void onNext(IntensityResponse intens) {
				// Print out response
				System.out.println("Brightness has been set to level " + intens.getIntensity());
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {

			}
		};

		StreamObserver<IntensityRequest> requestObserver = lasyncStub.lightIntensity(responseObserver);
		try {
			// send a stream of requests
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(50).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(60).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(80).build());
			System.out.println("Lights brightness changed");
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(100).build());
			System.out.println("Lights brightness changed");
			
			
			Thread.sleep(new Random().nextInt(2000) + 1000);
			// catch any errors
		} catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
		requestObserver.onCompleted();
	}
	
	//DEVICES SERVICES
public static void switchDevices(){
		
		DevicesRequest request = DevicesRequest.newBuilder().setDevices(false).build();

		DevicesResponse response = ublockingStub.switchDevices(request);

		if (response.getDevices()) {
			System.out.println("Devices off!");
		}
		else {
			System.out.println("Devices on!");
		}
	}
		
	public static void switchCameraOn(){
		
		CameraRequest request = CameraRequest.newBuilder().setCamera(false).build();

		CameraResponse response = ublockingStub.switchCameraOn(request);

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

		StreamObserver<PrinterRequest> requestObserver = uasyncStub.printList(responseObserver);
			try {
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Print").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("These").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Files").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Please").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Thanks").build());
				
				Thread.sleep(new Random().nextInt(1000) + 2000);

			} catch (RuntimeException e) {
	            requestObserver.onError(e);
	            	throw e;
	            	
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	        }
			requestObserver.onCompleted();
	}
}
