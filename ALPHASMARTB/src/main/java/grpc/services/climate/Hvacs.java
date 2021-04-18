package grpc.services.climate;
import java.util.ArrayList;



public class Hvacs extends ArrayList<Hvac>{
	
	 public static Hvacs hvacs;

	    public static Hvacs getInstance() {
	        if (hvacs == null) {
	        	hvacs = new Hvacs();
	        }
	        return hvacs;
	    }

	    public Hvacs() {
	        this.add(grpc.services.climate.Hvac.newBuilder()
	                .setHvacId(1)
	                .setStatus("ON")
	                .setTemperature(1)
	                .build());

}
}
