package jmDNS;

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.services.light.LightData;

public class ServiceRegistration {
	public static JmDNS jmdns;

	 public static void main(String[] args) throws InterruptedException {
	 }

	 public void jmndsRegister(int climatePort, int lightPort, int utilityPort)	throws InterruptedException {

			LightData myLightData = new LightData();

			try {
				// Create a JmDNS instance
				jmdns = JmDNS.create(InetAddress.getLocalHost());
				// Registering all services
				System.out.println("Register Service");
				ServiceInfo serviceUtil = ServiceInfo.create("_http._tcp.local.", "utility", utilityPort, "path=index.html");
				ServiceInfo serviceClime = ServiceInfo.create("_http._tcp.local.", "climate", climatePort, "path=index.html");
				ServiceInfo serviceLight = ServiceInfo.create("_http._tcp.local.", "light", lightPort,"path=index.html");

				jmdns.registerService(serviceUtil);
				jmdns.registerService(serviceClime);
				jmdns.registerService(serviceLight);

				 //Wait
				 Thread.sleep(10000);

				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		public void unRegister() {
			System.out.println("Unregistering services");

			jmdns.unregisterAllServices();

		}
}
