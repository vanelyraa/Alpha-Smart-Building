package grpc.services.light;

import java.util.ArrayList;

public class LightData {
	
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
	
}



	