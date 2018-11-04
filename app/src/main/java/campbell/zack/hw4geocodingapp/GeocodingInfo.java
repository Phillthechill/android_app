package campbell.zack.hw4geocodingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

public class GeocodingInfo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoding_info);

        Intent intent = getIntent();
        String jsonString = intent.getStringExtra(HomepageActivity.GEOCODING_MESSAGE);

        try {
            JSONObject object = getJSON(jsonString);

            String locationType = object.getJSONObject("geometry").getString("location_type");
            Double latitude = object.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            Double longitude = object.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            String placeId = object.getString("place_id");

            TextView latText = findViewById(R.id.latTextBox);
            TextView lngText = findViewById(R.id.lngTextBox);
            TextView typeOfLocText = findViewById(R.id.typeLocText);
            TextView placeIdText = findViewById(R.id.placeIdText);

            latText.setText(latitude.toString());
            lngText.setText(longitude.toString());
            typeOfLocText.setText(locationType);
            placeIdText.setText(placeId);

        } catch (Exception e) {
            e.getMessage();
        }


    }

    private JSONObject getJSON(String s) throws Exception {
        JSONObject object = new JSONObject(s);
        if(! object.getString("status").equals("OK")){
            return null;
        }
        return object.getJSONArray("results").getJSONObject(0);
    }

}
