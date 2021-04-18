package grpc.services.climate;
import java.util.ArrayList;

public class CoLevels extends ArrayList<CoLevel> {

	 public static CoLevels coLevel;

	    public static CoLevels getInstance() {
	        if (coLevel == null) {
	        	coLevel = new CoLevels();
	        }
	        return coLevel;
	    }

	    public CoLevels() {
	        this.add(grpc.services.climate.CoLevel.newBuilder()
	                .setBuilding("Auditorium")
	                .setCoNow(5)
	                .build());

}
}