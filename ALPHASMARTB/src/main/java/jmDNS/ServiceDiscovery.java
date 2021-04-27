package jmDNS;

/*import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class ServiceDiscovery {
	
	private static class Listener implements ServiceListener {
		
		
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}

		
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}

		
		public void serviceResolved(ServiceEvent event) {
					System.out.println("Service resolved: " + event.getInfo());
					System.out.println("Get Name: " + event.getName() + " PORT: " + event.getInfo().getPort());
					/*ServiceInfo info = event.getInfo();
		            int port = info.getPort();
		            String path = info.getNiceTextString().split("=")[1];

		            String url = "http://localhost:" + port + "/" + path;
		            System.out.println(" --- sending request to " + url);
					
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Add a service listener
			jmdns.addServiceListener("_http._tcp.local.", new Listener());
			System.out.println("Listener working");
			// Wait a bit
            Thread.sleep(20000);
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}*/
