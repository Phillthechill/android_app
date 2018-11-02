package campbell.zack.hw4geocodingapp;

public class information {
    double lat;
    double lng;
    String location;

    public information(){
        this.lat = 0.0;
        this.lng = 0.0;
        this.location = " ";
    }

    public information(double latitude, double longitude, String loc){
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

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getLocation() {
        return location;
    }
}
