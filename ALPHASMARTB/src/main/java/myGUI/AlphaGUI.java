package myGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.JFrame;

import grpc.services.climate.ClimateServiceGrpc;
import grpc.services.light.LightServiceGrpc;
import grpc.services.utility.UtilityServiceGrpc;

public class AlphaGUI implements ActionListener{

	private JFrame frame;
	private static int climatePort, utilityPort, lightPort = 0;
	private static String host = "localhost";
	private static ClimateServiceGrpc.ClimateServiceBlockingStub cblockingStub;
	private static ClimateServiceGrpc.ClimateServiceStub casyncStub;
	private static LightServiceGrpc.LightServiceBlockingStub lblockingStub;
	private static LightServiceGrpc.LightServiceStub lasyncStub;
	private static UtilityServiceGrpc.UtilityServiceBlockingStub ublockingStub;
	private static UtilityServiceGrpc.UtilityServiceStub uasyncStub;
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		AlphaGUI window = new AlphaGUI();
		
		//window.build();
		
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // add service listeners for User, Utilities, and News server
            jmdns.addServiceListener("_climate._tcp.local.", new AlphaGUI.Listener());
            jmdns.addServiceListener("_light._tcp.local.", new AlphaGUI.Listener());
            jmdns.addServiceListener("_utility._tcp.local.", new AlphaGUI.Listener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
		
		
		
	

	