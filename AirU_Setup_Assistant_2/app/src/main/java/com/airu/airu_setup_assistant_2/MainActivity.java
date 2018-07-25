package com.airu.airu_setup_assistant_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.wifi.WifiManager;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("AirU Setup Assistant");

//        Button buttonOne = (Button) findViewById(R.id.button);
//        buttonOne.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                final WifiManager mainWifiObj;
//                mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//                if (mainWifiObj.isWifiEnabled() == false)
//                {
//                    //Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
//                    mainWifiObj.setWifiEnabled(true);
//                }
//
//                registerReceiver(new BroadcastReceiver()
//                {
//                    @Override
//                    public void onReceive(Context c, Intent intent)
//                    {
//                        List<ScanResult> results = mainWifiObj.getScanResults();
//                        size = results.size();
//                    }
//                }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

//                class WifiScanReceiver extends BroadcastReceiver {
//                    public void onReceive(Context c, Intent intent) {
//                    }
//                }
//
//                WifiScanReceiver wifiReciever = new WifiScanReceiver();
//                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
//
//                List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
                //String data = wifiScanList.get(0).toString();

                //Log.d("test_wifi_item", data);
//            }
//        });
    }
}
