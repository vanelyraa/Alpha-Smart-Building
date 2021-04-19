package grpc.services.energy;

import java.util.ArrayList;


public class LightsData extends ArrayList<Lights> {

	public static LightsData lightsdata;

    public static LightsData getInstance() {
        if (lightsdata == null) {
        	lightsdata = new LightsData();
        }
        return lightsdata;
    }

    public LightsData() {
        this.add(grpc.services.energy.Lights.newBuilder()
                .setLightId(1)
                .setLightStatus("ON")
                .setAdjust(1)
                .build());

}
}
