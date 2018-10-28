package campbell.zack.hw4geocodingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomepageActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findLoc(View view) {
        Intent intent = new Intent(this, MapsActivity.class);

        // USE putExtra() on the intent to send processed data to the map

        startActivity(intent);
    }

}
