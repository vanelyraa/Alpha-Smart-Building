package grpc.services.occupancy;

import java.util.ArrayList;

public class Occupancys extends ArrayList<Occupancy> {

	 public static Occupancys occupancy;

	    public static Occupancys getInstance() {
	        if (occupancy == null) {
	        	occupancy = new Occupancys();
	        }
	        return occupancy;
	    }

	    public Occupancys() {
	        this.add(grpc.services.occupancy.Occupancy.newBuilder()
	                .setRoom("Auditorium")
	                .setLocalOccupancy(5)
	                .build());

}
}
