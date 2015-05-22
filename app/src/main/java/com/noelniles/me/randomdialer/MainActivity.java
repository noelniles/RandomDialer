package com.noelniles.me.randomdialer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

    private String buttonLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLabel = new String("Press me");
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setText(buttonLabel);
        button1.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void callNumber(String number){
        String numberToDial = "tel:"+number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(numberToDial)));
    }

    @Override
    public void onClick(View v) {
        TelecomManager tman = (TelecomManager)android.content.Context.getSystemService(Context.TELECOM_SERVICE);
        boolean busy = tman.isInCall();
        String phoneNumber = new String();

        for (int i = 0; i < 10000; i++) {
            phoneNumber = String.format("%04d", i);
            if (!busy) {
              callNumber(phoneNumber);
            }
        }
    }
}
