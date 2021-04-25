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
import grpc.services.utility.PrinterRequestOrBuilder;
import grpc.services.utility.PrinterResponse;
import grpc.services.utility.UtilityServer;
import grpc.services.utility.UtilityServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import jmDNS.ServiceRegistration;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

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
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class SmartGUIclient{

	private JFrame frame;
	private static int climatePort,utilityPort, lightPort = 0;
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
	public String IdLights;
	public JLabel lightDataId;
	public JLabel lightDataStatus;
	public JLabel lightDataIntensity;
	
	
	
	/**
	 * Launch the application.
	 */
	
	public static class Listener implements ServiceListener {
	    @Override
	    public void serviceAdded(ServiceEvent serviceEvent) {
	        System.out.println("Service added: " + serviceEvent.getInfo());
	    }

	    @Override
	    public void serviceRemoved(ServiceEvent serviceEvent) {
	        System.out.println("Service removed: " + serviceEvent.getInfo());
	    }

	    @Override
	    public void serviceResolved(ServiceEvent serviceEvent) {
	        System.out.println("Service resolved: " + serviceEvent.getInfo());
	        if (serviceEvent.getName().equals("climate")) {
	            climatePort = serviceEvent.getInfo().getPort();
	        } else if (serviceEvent.getName().equals("utilitiy")) {
	            utilityPort = serviceEvent.getInfo().getPort();
	        } else if (serviceEvent.getName().equals("light")){
	            lightPort = serviceEvent.getInfo().getPort();
	        }
	        
	        
	    }
	}
		
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
		
		/*discoverService();
		jmndsRegister(climatePort, lightPort, utilityPort);*/
		
		initialize();
		
		ServiceRegistration reg = new ServiceRegistration();
		reg.jmndsRegister(climatePort, lightPort, utilityPort);
		
		discoverService();
		reg.unRegister();
				
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
		
		private void discoverService() {
			
			
			try {
				// Create a JmDNS instance
				JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				jmdns.addServiceListener("_climate._tcp.local.", new SmartGUIclient.Listener());
		            jmdns.addServiceListener("_utilitiy._tcp.local.", new SmartGUIclient.Listener());
		            jmdns.addServiceListener("_light._tcp.local.", new SmartGUIclient.Listener());
		        } catch (UnknownHostException e) {
		            System.out.println(e.getMessage());
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}


	/**
	 * Initialize the contents of the frame.
	 */


	private void initialize() {
		frame = new JFrame("Alpha Smart dashboard");
		frame.getContentPane().setForeground(new Color(255, 228, 225));
		frame.setBounds(100, 100, 413, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 228, 225));
		
		JLabel TitleLabel = new JLabel("Alpha Smart Building Solutions");
		TitleLabel.setForeground(new Color(220, 20, 60));
		TitleLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 26));
		TitleLabel.setBounds(10, 7, 385, 52);
		frame.getContentPane().add(TitleLabel);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/logo.png"));
		
		JToggleButton hvacOnOff = new JToggleButton("On");
		hvacOnOff.setBounds(166, 151, 65, 23);
		frame.getContentPane().add(hvacOnOff);
		hvacOnOff.setSelected(true);
		hvacOnOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (hvacOnOff.isSelected()){
					hvacOnOff.setText("On");
					Switch(true);

	            } else {
	            	hvacOnOff.setText("Off");
	            	Switch(false);
	            }
			}
		});
		
		JLabel hvacLabel = new JLabel("HVAC");
		hvacLabel.setForeground(new Color(220, 20, 60));
		hvacLabel.setBounds(175, 126, 41, 14);
		frame.getContentPane().add(hvacLabel);
		hvacLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton coButton = new JButton("Check");
		coButton.setBounds(91, 339, 75, 23);
		coButton.setBorderPainted(false);
		coButton.setBackground(new Color(240, 128, 128));
		frame.getContentPane().add(coButton);
		coButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCO();
			}
		});
		
		JLabel coLabel = new JLabel("CO level");
		coLabel.setForeground(new Color(220, 20, 60));
		coLabel.setBounds(32, 341, 65, 14);
		frame.getContentPane().add(coLabel);
		coLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton tempPlus = new JButton("+");
		tempPlus.setBorderPainted(false);
		tempPlus.setBackground(new Color(240, 128, 128));
		tempPlus.setBounds(277, 258, 41, 23);
		frame.getContentPane().add(tempPlus);
		tempPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(+1);
				HvacTemperature(1);
			}
		});
		
		JButton tempMinus = new JButton("-");
		tempMinus.setBorderPainted(false);
		tempMinus.setBackground(new Color(240, 128, 128));
		tempMinus.setBounds(313, 258, 41, 23);
		frame.getContentPane().add(tempMinus);
		tempMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(-1);
				HvacTemperature(-1);
			}
		});
		
		JLabel tempLabel = new JLabel("Temperature");
		tempLabel.setForeground(new Color(220, 20, 60));
		tempLabel.setBounds(277, 233, 79, 14);
		frame.getContentPane().add(tempLabel);
		tempLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel climateMainLabel = new JLabel("ADJUSTS");
		climateMainLabel.setForeground(new Color(220, 20, 60));
		climateMainLabel.setBounds(32, 197, 81, 25);
		frame.getContentPane().add(climateMainLabel);
		climateMainLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel logo = new JLabel("");
		logo.setBounds(0, 413, 166, 128);
		frame.getContentPane().add(logo);
		logo.setIcon(img);
		
		JLabel homeWelcome = new JLabel("Welcome to our Dashboard!");
		homeWelcome.setForeground(new Color(220, 20, 60));
		homeWelcome.setBounds(100, 63, 218, 16);
		frame.getContentPane().add(homeWelcome);
		homeWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lightMainLabel = new JLabel("LIGHTS");
		lightMainLabel.setForeground(new Color(220, 20, 60));
		lightMainLabel.setBounds(257, 304, 85, 22);
		frame.getContentPane().add(lightMainLabel);
		lightMainLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lightChoice = new JLabel("Choose light:");
		lightChoice.setForeground(new Color(220, 20, 60));
		lightChoice.setBounds(219, 341, 102, 14);
		frame.getContentPane().add(lightChoice);
		lightChoice.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		JLabel intensityLabel = new JLabel("Brightness");
		intensityLabel.setForeground(new Color(220, 20, 60));
		intensityLabel.setBounds(32, 233, 102, 14);
		frame.getContentPane().add(intensityLabel);
		intensityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton intensPlus = new JButton("+");
		intensPlus.setBorderPainted(false);
		intensPlus.setBackground(new Color(240, 128, 128));
		intensPlus.setBounds(32, 258, 41, 23);
		frame.getContentPane().add(intensPlus);
		intensPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(+1);
				lightIntensity(1);
			}
		});
		
		JButton intensMinus = new JButton("-");
		intensMinus.setBorderPainted(false);
		intensMinus.setBackground(new Color(240, 128, 128));
		intensMinus.setBounds(72, 258, 41, 23);
		frame.getContentPane().add(intensMinus);
		intensMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(-1);
				lightIntensity(-1);
			}
		});
		
		JLabel lightOnOff = new JLabel("Lights");
		lightOnOff.setForeground(new Color(220, 20, 60));
		lightOnOff.setBounds(239, 381, 59, 14);
		frame.getContentPane().add(lightOnOff);
		lightOnOff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JToggleButton lightbutton = new JToggleButton("On");
		lightbutton.setBounds(304, 379, 59, 23);
		frame.getContentPane().add(lightbutton);
		lightbutton.setSelected(true);
		lightbutton.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if (lightbutton.isSelected()){
					lightbutton.setText("On");
					LightsOnOff(true);
	            } 
				 else {
					 lightbutton.setText("Off");
					 LightsOnOff(false);
	            }
				
			}
		});
		
		JLabel utilMainLabel = new JLabel("SWITCHES");
		utilMainLabel.setForeground(new Color(220, 20, 60));
		utilMainLabel.setBounds(34, 90, 114, 25);
		frame.getContentPane().add(utilMainLabel);
		utilMainLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel deviceLabel = new JLabel("Devices");
		deviceLabel.setForeground(new Color(220, 20, 60));
		deviceLabel.setBounds(33, 126, 52, 14);
		frame.getContentPane().add(deviceLabel);
		deviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JToggleButton deviceOnOff = new JToggleButton("On");
		deviceOnOff.setBounds(35, 151, 62, 23);
		frame.getContentPane().add(deviceOnOff);
		deviceOnOff.setSelected(true);
		deviceOnOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (deviceOnOff.isSelected()){
					deviceOnOff.setText("On");
					switchDevices(true);
	            } 
				 else {
					 deviceOnOff.setText("Off");
					 switchDevices(false);
	            }
			}
		});
		
		JLabel VisitLabel = new JLabel("Visit list");
		VisitLabel.setForeground(new Color(220, 20, 60));
		VisitLabel.setBounds(32, 381, 59, 14);
		frame.getContentPane().add(VisitLabel);
		VisitLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton printLabel = new JButton("Print");
		printLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printList();
			}
		});
		printLabel.setBorderPainted(false);
		printLabel.setBackground(new Color(240, 128, 128));
		printLabel.setBounds(91, 379, 75, 23);
		frame.getContentPane().add(printLabel);
		
		JLabel cameraLabel = new JLabel("Cameras");
		cameraLabel.setForeground(new Color(220, 20, 60));
		cameraLabel.setBounds(304, 126, 59, 14);
		frame.getContentPane().add(cameraLabel);
		cameraLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JToggleButton cameraOnOff = new JToggleButton("On");
		cameraOnOff.setBounds(304, 151, 59, 23);
		frame.getContentPane().add(cameraOnOff);
		cameraOnOff.setSelected(true);
		cameraOnOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cameraOnOff.isSelected()){
					cameraOnOff.setText("On");
					switchCameraOn(true);
	            } 
				 else {
					 cameraOnOff.setText("Off");
					 switchCameraOn(false);
	            }
			}
		});
		
		JLabel lblControls = new JLabel("CONTROLS");
		lblControls.setForeground(new Color(220, 20, 60));
		lblControls.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblControls.setBounds(32, 303, 105, 25);
		frame.getContentPane().add(lblControls);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 57, 395, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 87, 395, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 184, 377, 2);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(10, 291, 377, 2);
		frame.getContentPane().add(separator_2_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(194, 306, 2, 209);
		frame.getContentPane().add(separator_3);
		
		String[] IdLights = {"Select a room","Conference Room", "Cafeteria", "Toilets", "Room 1", "Room 2"};
		JComboBox comboBox = new JComboBox(IdLights);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox)e.getSource();
		        String IdLights = (String)comboBox.getSelectedItem();
		        getLightId();
			}
		});
		comboBox.setBounds(324, 339, 30, 22);
		frame.getContentPane().add(comboBox);
		
	}

	//GRPC
	
	//HVAC SERVICES
	public static void Switch(boolean OnOffH){
		
		SwitchRequest request = SwitchRequest.newBuilder().setPower(OnOffH).build();

		SwitchResponse response;

		try {
			response = cblockingStub.hvacOnOff(request);
		}catch(StatusRuntimeException e) {
			System.out.println("RPC switch HVAC failed:"+ e.getStatus());
			return;
		}
		if (response.getPower()) {
			System.out.println("HVAC on");
		}
		else {
			System.out.println("HVAC off");
		}
	}

	public static void HvacTemperature(int temperature){
		HvacRequest request = HvacRequest.newBuilder().setTemp(temperature).build();
		System.out.println("Seting room temperature");

		StreamObserver<HvacResponse> responseObserver = new StreamObserver<HvacResponse>() {

			
			@Override
			public void onNext(HvacResponse tempNew) {
				System.out.println("Change to : " + tempNew + " Celsius");
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
		
			}
	
	public void checkCO(){
		int CoNow = 45;
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(CoNow).build();
		ExtractionResponse response;
		
		try {
			response = cblockingStub.checkCO(request);
		}catch(StatusRuntimeException e) {
			System.out.println("RPC CO level failed:"+ e.getStatus());
			return;
		}
		if (CoNow > 40) {
			System.out.println("Co level is: " + response.getLevel() + "now");
			System.out.println("High level of CO, extractor on!");
		}
		else {
			System.out.println("CO level OK!");
		}
		
		
		/*int CoNow = 45;
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(CoNow).build();

		ExtractionResponse response = cblockingStub.checkCO(request);

		if (response.getLevel() > 40) {
			System.out.println("Co level is: " + response.getLevel() + "now");
			System.out.println("High level of CO, extractor on!");
		}
		else {
			System.out.println("CO level OK!");
		}*/
	}
	
	
	//LIGHTS SERVICES
	public void lighting(){
		Empty emp = Empty.newBuilder().build();
		
		System.out.println("Lights");
		LightingResponse response;
		//Error Handling
		try {
			 response = lblockingStub.lighting(emp);
			 
		}catch(StatusRuntimeException e) {
			System.out.println("RPC lighiting failed:"+ e.getStatus());
			return;
		}
		String intens = String.valueOf(response.getIntensity());
		lightDataId.setText("LightID: " + response.getLightId());
		lightDataStatus.setText("Light status: " + response.getStatus());
		lightDataIntensity.setText("Brightness: " + intens);		
	}

	public void LightsOnOff(boolean OnOffL){
		//String aLight = getLightId();
		LightsRequest request = LightsRequest.newBuilder().setSwitch(OnOffL).build();
		
		// check the response
		LightsResponse response;
		try {
			response = lblockingStub.lightsOnOff(request);
		}catch(StatusRuntimeException e) {
			System.out.println("RPC light switch failed:"+ e.getStatus());
			return;
		}
		
		// print appropriate message depending on response
		Boolean statusLight = response.getSwitch();
		if (statusLight) {
			lightDataStatus.setText("Lights turned on!");
		}
		else {
			lightDataStatus.setText("Lights off!");
		}
	}
	
	public void lightIntensity(int bright){
		StreamObserver<IntensityResponse> responseObserver = new StreamObserver<IntensityResponse>() {

			@Override
			public void onNext(IntensityResponse value) {
				// Print out response
				System.out.println("Brightness has been set to level " + value);
				String intensity = String.valueOf(value.getIntensity());
				lightDataIntensity.setText("Brightness: "+intensity);
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
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(bright).build());
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
	public static void switchDevices(boolean OnOffD){
		
		DevicesRequest request = DevicesRequest.newBuilder().setDevices(OnOffD).build();

		DevicesResponse response;
		try {
			response = ublockingStub.switchDevices(request);
		}
		catch(StatusRuntimeException e) {
			System.out.println("RPC devices failed: {0}"+ e.getStatus());
			return;
		}
		if (response.getDevices()) {
			System.out.println("Devices on");
		}
		else {
			System.out.println("Devices off");
		}
	}
		
	public static void switchCameraOn(boolean OnOffC){
		
		CameraRequest request = CameraRequest.newBuilder().setCamera(OnOffC).build();

		CameraResponse response;
		
		try {
			 response = ublockingStub.switchCameraOn(request);

		}catch(StatusRuntimeException e) {
			System.out.println("RPC camera failed:"+ e.getStatus());
			return;
		}
		if (response.getCamera()) {
			System.out.println("Motion detected, camera on!");
		}
		else {
			System.out.println("Camera off!");
		}
	}
	
	public static void printList(){
		
		ArrayList<String> listOfVisits = new ArrayList<>();

		StreamObserver<PrinterResponse> responseObserver = new StreamObserver<PrinterResponse>() {

			@Override
			public void onNext(PrinterResponse value) {
				System.out.println("Printing updated visit list: " + value.getPList());
				listOfVisits.add(value.getPList());
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("GRPC printer failed: " + t.getMessage());
	               t.printStackTrace();
	           
			}

			@Override
			public void onCompleted() {
				 
	               System.out.println("\nList completed");
			}

		};

		StreamObserver<PrinterRequest> requestObserver = uasyncStub.printList(responseObserver);
		try {
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Walter Knutz").build());
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Maria Stunnten").build());
			
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Rodrigo Salez").build());
			
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Jessica Klint").build());
		
				requestObserver.onNext(PrinterRequest.newBuilder().setPList("Jeniffer Kellei").build());
			
				 Thread.sleep(1000);
		    	   
	       } catch (RuntimeException e) {
	    	   System.out.println("RPC printer failed: " + e.getMessage());
				requestObserver.onError(e);
				throw e;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
				
				
			requestObserver.onCompleted();
			System.out.println("\nVisitor List: " + listOfVisits.size());
            for(String visits : listOfVisits) {
            	System.out.println(visits);
                JOptionPane.showMessageDialog(null, visits);
            }
				
	}

	public String getLightId() {
		return IdLights;
	}
}

				


