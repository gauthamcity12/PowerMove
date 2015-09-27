package io.github.gauthamcity12.powermove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ChoiceActivity extends Activity {

    private TextView nameView;
    private TextView phoneView;
    private TextView ratingView;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String url = intent.getStringExtra("image");
        String phone = intent.getStringExtra("phone");
        String rating = intent.getStringExtra("rating");

        nameView = (TextView) findViewById(R.id.name);
        phoneView = (TextView) findViewById(R.id.phone);
        ratingView = (TextView) findViewById(R.id.rating);

        nameView.setText(name);
        phoneView.setText(phone);
        ratingView.setText(rating);

//        URL url2 = null;
//        Bitmap bmp = null;
//        try {
//            url2 = new URL(url);
//            bmp = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //imgView = (ImageView) findViewById(R.id.image);
        //imgView.setImageBitmap(bmp);
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

    public void tooExpensive(View view){

    }
}
