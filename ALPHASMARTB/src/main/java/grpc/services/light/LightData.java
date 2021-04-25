package grpc.services.light;

import java.util.ArrayList;

public class LightData extends ArrayList<LightingResponse> {

	private String type, typeId;
	private boolean on;
	private int intensity;
	public static LightData lights;

	
	public LightData() {
		this.type="Lighs";
		this.on=true;
		this.typeId="Light ID";
		this.intensity=4;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String gettypeId() {
		return typeId;
	}

	public void settypeId(String typeId) {
		this.typeId = typeId;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
	
	public static LightData getInstance() {
		if(lights == null) {
			lights = new LightData();
		}
		return lights;
	}
	
	public void LightDataArray() {
		
		//ArrayList<LightingResponse> listOfLights = new ArrayList<>();
		this.add(LightingResponse.newBuilder().setLightId("Conference Room").setIntensity(1).setStatus("On").build());
		this.add(LightingResponse.newBuilder().setLightId("Cafeteria").setIntensity(1).setStatus("On").build());
		this.add(LightingResponse.newBuilder().setLightId("Room 1").setIntensity(3).setStatus("On").build());
		this.add(LightingResponse.newBuilder().setLightId("Room 2").setIntensity(4).setStatus("On").build());
		this.add(LightingResponse.newBuilder().setLightId("Toilets").setIntensity(2).setStatus("On").build());
	}
}

	