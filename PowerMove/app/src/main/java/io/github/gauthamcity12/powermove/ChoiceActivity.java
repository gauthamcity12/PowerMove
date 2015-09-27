package io.github.gauthamcity12.powermove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


public class ChoiceActivity extends Activity {

    private TextView nameView;
    private TextView phoneView;
    private TextView ratingView;
    private TextView snippetView;
    private ImageView imgView;
    private String postalCode;
    private String name;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("image");
        String phone = intent.getStringExtra("phone");
        String rating = intent.getStringExtra("rating");
        String snippet = intent.getStringExtra("snippet");
        postalCode = intent.getStringExtra("postalCode");


        nameView = (TextView) findViewById(R.id.name);
        phoneView = (TextView) findViewById(R.id.phone);
        ratingView = (TextView) findViewById(R.id.rating);
        snippetView = (TextView) findViewById(R.id.snippet);

        nameView.setText(name);
        phoneView.setText(phone);
        ratingView.setText("Rating: "+rating);
        snippetView.setText(snippet);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void randomize(View view){
        Object obj = new MyTask().execute(postalCode);
    }

    public void nextActivity(String[] arr){
        Intent intent = new Intent(this, ChoiceActivity.class);
        intent.putExtra("name", arr[0]);
        intent.putExtra("image", arr[1]);
        intent.putExtra("phone", arr[2]);
        intent.putExtra("rating", arr[3]);
        intent.putExtra("snippet", arr[4]);
        intent.putExtra("postalCode", postalCode);
        startActivity(intent);
    }

    public void powerMove(View view){
        String uri = String.format(Locale.ENGLISH, "geo:");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    private class MyTask extends AsyncTask<String, Void, String[]> {

        private static final String CONSUMER_KEY = "5si1y_o6NPOuDxsbM_6Dmg";
        private static final String CONSUMER_SECRET = "pmWjujMIW_ibx-1x6Q_vsF_CaS0";
        private static final String TOKEN = "4Yn1h3QzMA-hah6Ps19MAkN8iLRwQww_";
        private static final String TOKEN_SECRET = "p-I-JEJbu5yxoIfkbjnK1J57Tyc";
        private YelpAPI.YelpAPICLI yelpApiCli;
        private YelpAPI yelpApi;

        @Override
        protected String[] doInBackground(String[] params) {
            yelpApiCli = new YelpAPI.YelpAPICLI();
            yelpApiCli.term = "restaurant";
            yelpApiCli.location = params[0];
            yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            String[] result = YelpAPI.queryAPI(yelpApi, yelpApiCli, 1);
            return result;
        }

        protected void onPostExecute(String[] result){
            ChoiceActivity.this.nextActivity(result);
        }

    }

    private class imageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            URL url2 = null;
            Bitmap bmp = null;
            try {
                url2 = new URL(url);
                bmp = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap bmp){
            imgView = (ImageView) findViewById(R.id.imageView);
            imgView.setImageBitmap(bmp);
        }
    }
}

