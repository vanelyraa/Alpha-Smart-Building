package jmDNS;

import java.io.IOException;
import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {
	
	public static JmDNS jmdns;
	
	public static void main(String[] args) throws InterruptedException {
	}
	 
	public void jmdnsRegister(int climatePort, int lightPort, int utilityPort)	throws InterruptedException {
	 
		try {
			// Creating a JmDNS instance
			jmdns = JmDNS.create(InetAddress.getLocalHost());
			
			// Registering all services
			System.out.println("Register Service");
			ServiceInfo serviceUtility = ServiceInfo.create("_http._tcp.local.", "utility", 50098, "path=index.html");
			ServiceInfo serviceClimate = ServiceInfo.create("_http._tcp.local.", "climate", 50099, "path=index.html");
			ServiceInfo serviceLight = ServiceInfo.create("_http._tcp.local.", "light", 50097,"path=index.html");

			jmdns.registerService(serviceUtility);
			jmdns.registerService(serviceClimate);
			jmdns.registerService(serviceLight);
			System.out.println("Registration Service Starting ! ");
			
			// Waiting a bit
			Thread.sleep(10000);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	 	}
	
	// Unregistering all services
	public void unRegister() {
		System.out.println("Un-registering");
		jmdns.unregisterAllServices();
	}
}
	


