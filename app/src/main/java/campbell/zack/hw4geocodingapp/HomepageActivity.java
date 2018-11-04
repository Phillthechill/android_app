package campbell.zack.hw4geocodingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static campbell.zack.hw4geocodingapp.MapsActivity.geocodeMethod;

public class HomepageActivity extends Activity {
    public final static String EXTRA_MESSAGE = "";
    private String API_KEY = "AIzaSyCeihitvV-5nX5Gk6p3iWbHVyYyIViEovI";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findLoc(View view) {
        Intent intent = new Intent(this, MapsActivity.class);

        EditText editText = findViewById(R.id.Address);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    public void findElevation(View view) {
        try {
            EditText editText = findViewById(R.id.Address);
            String message = editText.getText().toString();

            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
            url += URLEncoder.encode(message,"UTF-8");
            url +="&key=" + API_KEY;

            String hold = new NetworkAsync().execute(url).get();
            information loc = geocodeMethod(hold);

            String eleURL = "https://maps.googleapis.com/maps/api/elevation/json?locations=";
            eleURL += loc.lat.toString() + "," + loc.lng.toString();
            eleURL +="&key=" + API_KEY;

            String eleMeters = new NetworkAsync().execute(eleURL).get();

            Double elevationMeters = getEle(eleMeters);
            Double elevationFeet = metersToFeetConv(elevationMeters);

            TextView elevationFeetText = findViewById(R.id.elevationFeet);
            TextView elevationMetersText = findViewById(R.id.elevationMeters);

            elevationFeetText.setText(elevationFeet.toString());
            elevationMetersText.setText(elevationMeters.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Double metersToFeetConv(Double meters) {
        return meters * 3.2808;
    }

    public static Double getEle(String address) throws Exception {
        JSONObject object = new JSONObject(address);
        if(! object.getString("status").equals("OK")){
            return null;
        }

        JSONObject result = object.getJSONArray("results").getJSONObject(0);
        return result.getDouble("elevation");
    }


}
