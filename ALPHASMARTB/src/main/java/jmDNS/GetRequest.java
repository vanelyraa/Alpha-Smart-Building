package jmDNS;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetRequest {

	    public static void request(String url) {
	        try {

	            Client client = Client.create();

	            WebResource webResource = client.resource(url);

	            ClientResponse response = webResource.get(ClientResponse.class);

	            if (response.getStatus() != 200) {
	                throw new RuntimeException("Failed : HTTP error code : "
	                        + response.getStatus());
	            }

	            String output = response.getEntity(String.class);

	            System.out.println("Output from Server .... \n");
	            System.out.println(output);
	            
	        } catch (Exception e) {

	            e.printStackTrace();

	        }
	    }
}
