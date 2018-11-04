package campbell.zack.hw4geocodingapp;

public class information {
    Double lat;
    Double lng;
    String location;

    public information(){
        this.lat = 0.0;
        this.lng = 0.0;
        this.location = " ";
    }

    public information(Double latitude, Double longitude, String loc){
        this.lat = latitude;
        this.lng = longitude;
        this.location = loc;
    }

    public void setLat (double latitude){
        this.lat = latitude;
    }

    public void setLng (double lng){
        this.lng = lng;
    }

    public void setLocation (String location){
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getLocation() {
        return location;
    }
}
