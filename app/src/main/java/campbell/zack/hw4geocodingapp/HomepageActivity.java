package campbell.zack.hw4geocodingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomepageActivity extends Activity {
    public final static String EXTRA_MESSAGE = "";
    public static String Message = new String();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findLoc(View view) {
        Intent intent = new Intent(this, MapsActivity.class);

        EditText editText = (EditText)findViewById(R.id.Address);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

}
