package io.github.gauthamcity12.powermove;

import android.app.Activity;
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

    private static final String CONSUMER_KEY = "5si1y_o6NPOuDxsbM_6Dmg";
    private static final String CONSUMER_SECRET = "pmWjujMIW_ibx-1x6Q_vsF_CaS0";
    private static final String TOKEN = "4Yn1h3QzMA-hah6Ps19MAkN8iLRwQww_";
    private static final String TOKEN_SECRET = "p-I-JEJbu5yxoIfkbjnK1J57Tyc";
    private Button foodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
        foodButton = (Button) findViewById(R.id.foodButton);
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
        Object obj = new MyTask().execute();
        Toast.makeText(this, "finished", Toast.LENGTH_SHORT);
    }

    private class MyTask extends AsyncTask {

        private static final String CONSUMER_KEY = "5si1y_o6NPOuDxsbM_6Dmg";
        private static final String CONSUMER_SECRET = "pmWjujMIW_ibx-1x6Q_vsF_CaS0";
        private static final String TOKEN = "4Yn1h3QzMA-hah6Ps19MAkN8iLRwQww_";
        private static final String TOKEN_SECRET = "p-I-JEJbu5yxoIfkbjnK1J57Tyc";
        private YelpAPI.YelpAPICLI yelpApiCli;
        private YelpAPI yelpApi;
        //public AsyncResponse delegate=null;

        @Override
        protected String doInBackground(Object[] params) {
            yelpApiCli = new YelpAPI.YelpAPICLI();
            yelpApiCli.term = "restaurant";
            yelpApiCli.location = "Atlanta, GA";
            yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            String result = YelpAPI.queryAPI(yelpApi, yelpApiCli);
            return result;
        }

        protected void onPostExecute(String result){
            Button foodButton = (Button) findViewById(R.id.foodButton);
            foodButton.setText(result);
            //delegate.processFinish(result);
        }

    }
}
