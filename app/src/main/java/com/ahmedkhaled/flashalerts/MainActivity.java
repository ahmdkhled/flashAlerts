package com.ahmedkhaled.flashalerts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button state;
    Setting setting;
    Boolean isOn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(getApplicationContext(),"sorry there is no flash ",Toast.LENGTH_SHORT).show();
            finish();
        }

        state= (Button) findViewById(R.id.setState);
        setting =new Setting(this);

        isOn=setting.getSatte();
        if (isOn){
            state.setText("ON");
            state.setTextColor(Color.WHITE);
            Log.d("TAG","on 1");

        }else {
            state.setText("OFF");
            state.setTextColor(Color.RED);
            Log.d("TAG","off 1");
        }

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOn=!isOn;
              if (isOn){
                  setting.setOnOrOff(true);
                  state.setText("ON");
                  state.setTextColor(Color.WHITE);
                  Log.d("TAG","on 2");
              }else {
                  setting.setOnOrOff(false);
                  state.setText("OFF");
                  state.setTextColor(Color.RED);
                  Log.d("TAG","off 3");
              }

            }
        });



    }

    void changeState(){

    }


}
