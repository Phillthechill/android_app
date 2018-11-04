package campbell.zack.hw4geocodingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private information place = new information();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        String add = intent.getStringExtra(HomepageActivity.EXTRA_MESSAGE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
            url += URLEncoder.encode(add,"UTF-8");
            url +="&key=AIzaSyCeihitvV-5nX5Gk6p3iWbHVyYyIViEovI";
            String hold = new NetworkAsync().execute(url).get();
            place = geocodeMethod(hold);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(place.getLat(), place.getLng());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker at "+place.getLocation() ));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public static information geocodeMethod (String address) throws Exception{
        JSONObject object = new JSONObject(address);
        if(! object.getString("status").equals("OK")){
            return null;
        }

        JSONObject result = object.getJSONArray("results").getJSONObject(0);
        JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
        return new information(location.getDouble("lat"),location.getDouble("lng"),result.getString("formatted_address"));
    }
}
