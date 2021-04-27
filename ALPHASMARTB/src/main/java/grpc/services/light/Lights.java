package grpc.services.light;

public class Lights {
    private String lightRequest;
    private String lightChange;

    public Lights() {

    }

    public Lights(String lightRequest, String lightChange) {
        this.lightRequest = lightRequest;
        this.lightChange = lightChange;
    }

    public String getLightRequest() {
        return lightRequest;
    }

    public void setLightRequest(String lightRequest) {
        this.lightRequest = lightRequest;
    }

    public String getCarChange() {
        return lightChange;
    }

    public void setLightChange(String lightChange) {
        this.lightChange = lightChange;
    }


}