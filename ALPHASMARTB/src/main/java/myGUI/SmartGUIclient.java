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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
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
import java.awt.SystemColor;

public class SmartGUIclient{

	private JFrame frame;
	//private static String host = "localhost";
	private static ClimateServiceGrpc.ClimateServiceBlockingStub cblockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub casyncStub;
	
	private static LightServiceGrpc.LightServiceBlockingStub lblockingStub;
	private static LightServiceGrpc.LightServiceStub lasyncStub;
	
	private static UtilityServiceGrpc.UtilityServiceBlockingStub ublockingStub;
	private static UtilityServiceGrpc.UtilityServiceStub uasyncStub;
	
	private static int climatePort = 50099;
	private static int lightPort = 50097;
	private static int utilityPort = 50098;
	public JLabel lightDataId;
	public JLabel lightDataStatus;
	public JLabel lightDataIntensity;
	int randomCo = (int)(Math.random() * 100 + 1);
	private static final int MIN = 1;
	private static final int MAX = 5;
	private static final int DEFAULT = 3;
	public static JTextField HTemp;
	public static  JTextField messages;
	
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
            // the ports for the connections are assigned depending on the service event info received
            if (serviceEvent.getName().equals("climate")) {
            	climatePort = serviceEvent.getInfo().getPort();
            } else if (serviceEvent.getName().equals("light")) {
            	lightPort = serviceEvent.getInfo().getPort();
            } else if (serviceEvent.getName().equals("utility")){
            	utilityPort = serviceEvent.getInfo().getPort();
            }
        }
    }	
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
		reg.jmdnsRegister(climatePort, lightPort, utilityPort);
		try {
            // create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // add service listeners for User, Utilities, and News server
            jmdns.addServiceListener("_http._tcp.local.", new SmartGUIclient.Listener());
            jmdns.addServiceListener("_http._tcp.local.", new SmartGUIclient.Listener());
            jmdns.addServiceListener("_http._tcp.local.", new SmartGUIclient.Listener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		ManagedChannel climatechannel = ManagedChannelBuilder.forAddress("localhost", 50099).usePlaintext().build();
		ManagedChannel lightchannel = ManagedChannelBuilder.forAddress("localhost", 50097).usePlaintext().build();
		ManagedChannel utilitychannel = ManagedChannelBuilder.forAddress("localhost", 50098).usePlaintext().build();
		cblockingStub = ClimateServiceGrpc.newBlockingStub(climatechannel);
		casyncStub = ClimateServiceGrpc.newStub(climatechannel);
		lblockingStub = LightServiceGrpc.newBlockingStub(lightchannel);
		lasyncStub = LightServiceGrpc.newStub(lightchannel);
		ublockingStub = UtilityServiceGrpc.newBlockingStub(utilitychannel);
		uasyncStub = UtilityServiceGrpc.newStub(utilitychannel);
		reg.unRegister();
		//InitialItem();
	}
	
	/*public void InitialItem() throws IOException, InterruptedException {
		lighting();
	}*/
		
		
	/**
	 * Initialize the contents of the frame.
	 */


	private void initialize() {
		
		/**
		 * Frame, labels, separators
		 */
		
		frame = new JFrame("Alpha Smart dashboard");
		frame.getContentPane().setForeground(new Color(255, 228, 225));
		frame.setBounds(100, 100, 413, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		
		JLabel TitleLabel = new JLabel("Alpha Smart Building Solutions");
		TitleLabel.setForeground(SystemColor.desktop);
		TitleLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 26));
		TitleLabel.setBounds(2, 0, 385, 52);
		frame.getContentPane().add(TitleLabel);
				
		JLabel homeWelcome = new JLabel("Welcome to our Dashboard!");
		homeWelcome.setForeground(SystemColor.desktop);
		homeWelcome.setBounds(100, 50, 218, 16);
		frame.getContentPane().add(homeWelcome);
		homeWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel logo = new JLabel("");
		logo.setBounds(0, 406, 166, 128);
		frame.getContentPane().add(logo);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/logo.png"));
		logo.setIcon(img);
		
		JLabel adjusts = new JLabel("ADJUSTS");
		adjusts.setForeground(SystemColor.desktop);
		adjusts.setBounds(32, 197, 81, 25);
		frame.getContentPane().add(adjusts);
		adjusts.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel switches = new JLabel("SWITCHES");
		switches.setForeground(SystemColor.desktop);
		switches.setBounds(32, 90, 114, 25);
		frame.getContentPane().add(switches);
		switches.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel Controls = new JLabel("CONTROLS");
		Controls.setForeground(SystemColor.desktop);
		Controls.setFont(new Font("Tahoma", Font.BOLD, 18));
		Controls.setBounds(32, 303, 105, 25);
		frame.getContentPane().add(Controls);
						
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 77, 395, 2);
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
				
		
		/**
		 * Climate buttons and labels
		 */
				
		JLabel hvacLabel = new JLabel("HVAC");
		hvacLabel.setForeground(SystemColor.desktop);
		hvacLabel.setBounds(179, 126, 41, 14);
		frame.getContentPane().add(hvacLabel);
		hvacLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel coLabel = new JLabel("CO level");
		coLabel.setForeground(SystemColor.desktop);
		coLabel.setBounds(32, 341, 65, 14);
		frame.getContentPane().add(coLabel);
		coLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel tempLabel = new JLabel("Temperature");
		tempLabel.setForeground(SystemColor.desktop);
		tempLabel.setBounds(277, 233, 79, 14);
		frame.getContentPane().add(tempLabel);
		tempLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		HTemp = new JTextField();
		HTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HvacTemperature();
			}
		});
		HTemp.setBounds(277, 258, 86, 20);
		frame.getContentPane().add(HTemp);
		HTemp.setColumns(10);
		
		JToggleButton hvacOnOff = new JToggleButton("Off");
		hvacOnOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		hvacOnOff.setBounds(166, 151, 65, 23);
		frame.getContentPane().add(hvacOnOff);
		hvacOnOff.setSelected(true);
		hvacOnOff.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (hvacOnOff.isSelected()){
					hvacOnOff.setText("Off");
					Switch(true);

	            } else {
	            	hvacOnOff.setText("On");
	            	Switch(false);
	            }
			}
		});
		
		
		JButton coButton = new JButton("Check");
		coButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		coButton.setBounds(91, 339, 75, 23);
		coButton.setBorderPainted(false);
		coButton.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(coButton);
		coButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCO();
			}
		});
		
				
		/**
		 * Utility buttons and labels
		 */
		
		JLabel deviceLabel = new JLabel("Devices");
		deviceLabel.setForeground(SystemColor.desktop);
		deviceLabel.setBounds(44, 126, 93, 14);
		frame.getContentPane().add(deviceLabel);
		deviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel VisitLabel = new JLabel("Visit list");
		VisitLabel.setForeground(SystemColor.desktop);
		VisitLabel.setBounds(32, 381, 59, 14);
		frame.getContentPane().add(VisitLabel);
		VisitLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel cameraLabel = new JLabel("Cameras");
		cameraLabel.setForeground(SystemColor.desktop);
		cameraLabel.setBounds(304, 126, 59, 14);
		frame.getContentPane().add(cameraLabel);
		cameraLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JToggleButton deviceOnOff = new JToggleButton("Off");
		deviceOnOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		deviceOnOff.setBounds(32, 150, 62, 23);
		frame.getContentPane().add(deviceOnOff);
		deviceOnOff.setSelected(true);
		deviceOnOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (deviceOnOff.isSelected()){
					deviceOnOff.setText("Off");
					switchDevices(true);
	            } 
				 else {
					 deviceOnOff.setText("On");
					 switchDevices(false);
	            }
			}
		});
		
		
		JButton printLabel = new JButton("Print");
		printLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		printLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printList();
			}
		});
		printLabel.setBorderPainted(false);
		printLabel.setBackground(SystemColor.activeCaption);
		printLabel.setBounds(91, 379, 75, 23);
		frame.getContentPane().add(printLabel);
		
		JToggleButton cameraOnOff = new JToggleButton("Off");
		cameraOnOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		cameraOnOff.setBounds(304, 151, 59, 23);
		frame.getContentPane().add(cameraOnOff);
		cameraOnOff.setSelected(true);
		cameraOnOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cameraOnOff.isSelected()){
					cameraOnOff.setText("Off");
					switchCameraOn(true);
	            } 
				 else {
					 cameraOnOff.setText("On");
					 switchCameraOn(false);
	            }
			}
		});
		
		
		
		/**
		 * Light buttons and labels
		 */
		
		JLabel lightMainLabel = new JLabel("LIGHTS");
		lightMainLabel.setForeground(SystemColor.desktop);
		lightMainLabel.setBounds(257, 304, 85, 22);
		frame.getContentPane().add(lightMainLabel);
		lightMainLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel intensityLabel = new JLabel("Brightness");
		intensityLabel.setForeground(SystemColor.desktop);
		intensityLabel.setBounds(42, 233, 75, 14);
		frame.getContentPane().add(intensityLabel);
		intensityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel office1light = new JLabel("Office 1");
		office1light.setForeground(SystemColor.desktop);
		office1light.setBounds(213, 341, 59, 14);
		frame.getContentPane().add(office1light);
		office1light.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel office2light = new JLabel("Office 2");
		office2light.setForeground(SystemColor.desktop);
		office2light.setFont(new Font("Tahoma", Font.PLAIN, 14));
		office2light.setBounds(213, 381, 59, 14);
		frame.getContentPane().add(office2light);
		
		JLabel receptionlight = new JLabel("Reception");
		receptionlight.setForeground(SystemColor.desktop);
		receptionlight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		receptionlight.setBounds(213, 420, 75, 14);
		frame.getContentPane().add(receptionlight);
				
		JToggleButton of1lightbutton = new JToggleButton("Off");
		of1lightbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		of1lightbutton.setBounds(295, 339, 59, 23);
		frame.getContentPane().add(of1lightbutton);
		of1lightbutton.setSelected(true);
		of1lightbutton.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if (of1lightbutton.isSelected()){
					of1lightbutton.setText("Off");
					LightsOnOff(true);
	            } 
				 else {
					 of1lightbutton.setText("On");
					 LightsOnOff(false);
	            }
				
			}
		});
				
		JToggleButton of2lightbutton = new JToggleButton("Off");
		of2lightbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		of2lightbutton.setSelected(true);
		of2lightbutton.setBounds(295, 379, 59, 23);
		frame.getContentPane().add(of2lightbutton);
		of2lightbutton.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if (of2lightbutton.isSelected()){
					of2lightbutton.setText("Off");
					LightsOnOff(true);
	            } 
				 else {
					 of2lightbutton.setText("On");
					 LightsOnOff(false);
	            }
				
			}
		});
		
		JToggleButton reclightbutton = new JToggleButton("Off");
		reclightbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		reclightbutton.setSelected(true);
		reclightbutton.setBounds(295, 418, 59, 23);
		frame.getContentPane().add(reclightbutton);
		reclightbutton.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (reclightbutton.isSelected()){
				reclightbutton.setText("Off");
				LightsOnOff(true);
            } 
			 else {
				 reclightbutton.setText("On");
				 LightsOnOff(false);
            }
			
		}
	});
		
		JSlider sliderbritness = new JSlider(JSlider.HORIZONTAL,MIN, MAX, DEFAULT);
		sliderbritness.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lightIntensity(sliderbritness.getValue());
			}
		});
		sliderbritness.setBounds(32, 258, 200, 26);
		frame.getContentPane().add(sliderbritness);
				sliderbritness.setMajorTickSpacing(5);
				sliderbritness.setMinorTickSpacing(1);
				sliderbritness.setPaintTicks(true);
				sliderbritness.setPaintLabels(true);
			
		
		
		messages = new JTextField();
		messages.setBounds(219, 471, 168, 33);
		frame.getContentPane().add(messages);
		messages.setColumns(10);
		
		
		
		
		
			
			
	}
	
	/**
	 * GRPC SERVICES
	 */
	
	/**
	 * HVAC SERVICES
	 */	
	
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
			//System.out.println("HVAC on");
			messages.setText("HVAC off");
		}
		else {
			//System.out.println("HVAC off");
			messages.setText("HVAC on");
		}
	}

	public static void HvacTemperature(){
		int newTemp = Integer.parseInt(HTemp.getText().toString());
		HvacRequest request = HvacRequest.newBuilder().setTemp(newTemp).build();
		
		System.out.println("Requesting to set office aircon to "+newTemp+"°C");
		
		StreamObserver<HvacResponse> responseObserver = new StreamObserver<HvacResponse>() {
			
			@Override
			public void onNext(HvacResponse value) {
				if(newTemp > 35 || newTemp < 15 ) {//start if
					messages.setText("Select temperature between 15°C and 35°C: ");
					//System.out.println("Select temperature between 15°C and 35°C: ");
				} else {
				messages.setText("\nChanging to: "+value.getTemp()+"°C");
				//System.out.println("Changing to: "+value.getTemp()+"°C");
			}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				//System.out.println("Reached the selected temperature: "+newTemp+"°C");
				messages.setText("\nTemperature acheived: "+newTemp+"°C");
			}
		};
		
		casyncStub.hvacTemperature(request, responseObserver);
		
		// stops the thread after 3 seconds
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
		/*HvacRequest request = HvacRequest.newBuilder().setTemp(temperature).build();
		System.out.println("Seting room temperature");

		StreamObserver<HvacResponse> responseObserver = new StreamObserver<HvacResponse>() {

			
			@Override
			public void onNext(HvacResponse tempNew) {
				System.out.println("Change to : " + tempNew + " Celsius");
				messages.setText("Change to : " + tempNew + " Celsius");
				
			}
	
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
	
			@Override
			public void onCompleted() {
				System.out.println("Done");
				messages.append("Done");
			}

		};

		casyncStub.hvacTemperature(request, responseObserver);
		
			}*/
	
	public void checkCO(){
		int CoNow = randomCo;
		CoLevelRequest request = CoLevelRequest.newBuilder().setLevel(CoNow).build();
		ExtractionResponse response;
		
		try {
			response = cblockingStub.checkCO(request);
		}catch(StatusRuntimeException e) {
			System.out.println("RPC CO level failed:"+ e.getStatus());
			return;
		}
		if (CoNow > 40) {
			//System.out.println("Co level: " + response.getLevel());
			//System.out.println("High level of CO, extractor on!");
			messages.setText("Co level: " + response.getLevel());
			messages.setText("High level of CO, extractor on!");
		}
		else {
			//System.out.println("Co level: " + response.getLevel());
			messages.setText("Co level: " + response.getLevel());
			//System.out.println("CO level OK!");
			messages.setText("CO level OK!");
		}	
		
	}
	
	/**
	 * LIGHTS SERVICES
	 */
	
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
		/*lightDataId.setText("LightID: " + response.getLightId());
		lightDataStatus.setText("Light status: " + response.getStatus());
		lightDataIntensity.setText("Brightness: " + intens);	*/	
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

			messages.setText("Lights off!");
		}
		else {

			messages.setText("Lights on!");
		}
	}
	
	public void lightIntensity(int bright){
final CountDownLatch finishLatch = new CountDownLatch(1);
		
		StreamObserver<IntensityRequest> requestObserver = lasyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
				.lightIntensity(new StreamObserver<IntensityResponse>() {
					@Override
					public void onNext(IntensityResponse value) {
						//System.out.println(value);
						messages.setText("\nFinal Light Setting: "+value.getIntensity());
					}
					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
						finishLatch.countDown();
					}
					@Override
					public void onCompleted() {
						//System.out.println("Lights set");
						messages.setText("\nLighting adjustment completed");
						finishLatch.countDown();
					}
				});
		
		
		try {
			// simulation of light adjustment request stream from the client
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(bright).build());
			/*requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(bright+1).build());
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(bright+2).build());
			requestObserver.onNext(IntensityRequest.newBuilder().setIntensity(bright+3).build());*/
			
			Thread.sleep(1500);
			
		} catch (RuntimeException e) {
			requestObserver.onError(e);
			throw e;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		requestObserver.onCompleted();		
	}
	
		/*StreamObserver<IntensityResponse> responseObserver = new StreamObserver<IntensityResponse>() {

			@Override
			public void onNext(IntensityResponse value) {
				// Print out response
				System.out.println("Brightness has been set to level " + value);
				String intensity = String.valueOf(value.getIntensity());
				messages.setText("Brightness: "+intensity);
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
			messages.setText("Lights brightness changed");
						
			Thread.sleep(new Random().nextInt(2000) + 1000);
			// catch any errors
		} catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
		requestObserver.onCompleted();
	}*/
	
	/**
	 * DEVICES SERVICES
	 */
	
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
			//System.out.println("Devices on");
			messages.setText("Devices off");
		}
		else {
			//System.out.println("Devices off");
			messages.setText("Devices on");
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
			//System.out.println("Camera off!");
			messages.setText("Camera off!");
		}
		else {
			//System.out.println("Camera on!");
			messages.setText("Camera on!");
		}
	}
	
	public static void printList(){
		
		ArrayList<String> listOfVisits = new ArrayList<>();

		StreamObserver<PrinterResponse> responseObserver = new StreamObserver<PrinterResponse>() {

			@Override
			public void onNext(PrinterResponse value) {
				//System.out.println("Printing updated visit list: " + value.getPList());
				messages.setText("Printing updated visit list: " + value.getPList());
				listOfVisits.add(value.getPList());
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("GRPC printer failed: " + t.getMessage());
	               t.printStackTrace();
	           
			}

			@Override
			public void onCompleted() {
				 
	               //System.out.println("\nList completed");
	               messages.setText("\nList completed");
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
			//System.out.println("\nVisitor List: " + listOfVisits.size());
			messages.setText("\nVisitor List: " + listOfVisits.size());
            for(String visits : listOfVisits) {
            	//System.out.println(visits);
            	JOptionPane.showMessageDialog(null, visits);
            }
				
	}
}

				


