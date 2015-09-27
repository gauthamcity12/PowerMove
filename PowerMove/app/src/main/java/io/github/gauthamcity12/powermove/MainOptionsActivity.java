package io.github.gauthamcity12.powermove;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beust.jcommander.JCommander;

import javax.xml.transform.Result;


public class MainOptionsActivity extends Activity {

    private String postalCode;
    public static String[] values = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
        Intent intent = getIntent();
        postalCode = intent.getStringExtra("postalCode");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_options, menu);
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

    public void forFood(View view){
        Toast.makeText(this, postalCode, Toast.LENGTH_SHORT).show();
        Object obj = new MyTask().execute(postalCode);
        Intent intent = new Intent(this, ChoiceActivity.class);
        intent.putExtra("name", values[0]);
        intent.putExtra("image", values[1]);
        intent.putExtra("phone", values[2]);
        intent.putExtra("rating", values[3]);
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
            String[] result = YelpAPI.queryAPI(yelpApi, yelpApiCli);
            return result;
        }

        protected void onPostExecute(String[] result){
            values = result;
            Button foodButton = (Button) findViewById(R.id.foodButton);
            foodButton.setText(result[0]);
        }

    }
}
