package io.github.gauthamcity12.powermove;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.beust.jcommander.JCommander;


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
        YelpAPI.YelpAPICLI yelpApiCli = new YelpAPI.YelpAPICLI();

        //new JCommander(yelpApiCli, new String[1]);
        yelpApiCli.term = "restaurant";
        yelpApiCli.location = "Atlanta, GA";

        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

        String result = YelpAPI.queryAPI(yelpApi, yelpApiCli);
        foodButton.setText(result);
    }
}
