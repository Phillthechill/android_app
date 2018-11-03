package campbell.zack.hw4geocodingapp;

import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class NetworkAsync extends AsyncTask<String, String, String> {
    protected AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... strings) {
        String addy = "";
        try {
            URL newURL = new URL(strings[0]);
            InputStream is = newURL.openStream();
            Scanner scan = new Scanner(is);

            while (scan.hasNext()){
                addy += scan.nextLine();
            }
            HomepageActivity.Message = addy;
            scan.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return addy;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
        super.onPostExecute(s);
    }
}
