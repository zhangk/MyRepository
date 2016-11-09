package com.example.devin.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLevel = null;
    private int batteryLevel;
    private int batteryScale;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();

            //获取当前电量，如未获取具体数值，则默认为0
            batteryLevel=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

            //获取最大电量，如未获取到具体数值，则默认为100
            batteryScale=intent.getIntExtra(BatteryManager.EXTRA_SCALE,100);

            if(batteryLevel*100/batteryScale < 20){

                textViewLevel.setTextColor(Color.RED);
                textViewLevel.setText((batteryLevel*100/batteryScale)+"%");

            }else {

                textViewLevel.setTextColor(Color.BLUE);
                textViewLevel.setText((batteryLevel*100/batteryScale)+"%");

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLevel = (TextView) findViewById(R.id.textViewBattery);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        //注册接收器以获取电量信息
        registerReceiver(broadcastReceiver,intentFilter);

    }
}
