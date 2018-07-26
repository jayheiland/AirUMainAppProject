package com.airu.airu_setup_assistant_2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class WiFiDemo extends Activity implements View.OnClickListener
{
    WifiManager wifi;
    ListView lv;
    TextView wifiListHeader;
    TextView airuDisplay;
    Button buttonScan;
    int size = 0;
    ScanResult airu_result;
    List<ScanResult> network_results;
    String selected_wifi_ssid;
    String selected_wifi_password;
    EditText passwordField;
    Button enterPassword;


    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> wifinameslist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("AirU Sensor Setup");

        wifiListHeader = findViewById(R.id.wifiListHeader);
        airuDisplay = findViewById(R.id.airuDisplay);
        buttonScan = findViewById(R.id.buttonScan);
        passwordField = findViewById(R.id.passwordField);
        enterPassword = findViewById(R.id.enterPassword);

        //hide the password field and button at first
        passwordField.setVisibility(TextView.INVISIBLE);
        enterPassword.setVisibility(TextView.INVISIBLE);

        buttonScan.setOnClickListener(this);
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_wifi_ssid = wifinameslist.get(position).get(ITEM_KEY);
                passwordField.setVisibility(TextView.VISIBLE);
                enterPassword.setVisibility(TextView.VISIBLE);
            }
        });
        enterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonScan.setVisibility(View.INVISIBLE);
                passwordField.setFocusable(false);
                hideKeyboardFrom(getApplicationContext(), v);
                selected_wifi_password = passwordField.getText().toString();
            }
        });

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
        this.adapter = new SimpleAdapter(WiFiDemo.this, wifinameslist, R.layout.row, new String[] { ITEM_KEY }, new int[] { R.id.list_value });
        lv.setAdapter(this.adapter);

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent)
            {
                List<ScanResult> temp_results = wifi.getScanResults();
                for(ScanResult item : temp_results){
                    if(item.SSID.contains("AirU-")){
                        airu_result = item;
                    }
                }
                network_results = temp_results;
                size = network_results.size();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        //start the wifi scan when app starts
        wifi.startScan();
    }

    public void onClick(View view) {
        passwordField.setText("");
        passwordField.setVisibility(TextView.INVISIBLE);
        enterPassword.setVisibility(TextView.INVISIBLE);
        wifinameslist.clear();
        wifi.startScan();

        Toast.makeText(this, "Scanning...." + size, Toast.LENGTH_SHORT).show();
        try {
            size = size - 1;
            ArrayList<String> unique_wifi_ssids = new ArrayList<String>();
            while (size >= 0) {
                String current_ssid = network_results.get(size).SSID;
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ITEM_KEY, current_ssid); // + "  " + network_results.get(size).capabilities);

                if (!network_results.get(size).SSID.contains("AirU-") && !unique_wifi_ssids.contains(current_ssid)) {
                    wifinameslist.add(item);
                }
                //remove wifi ssids that are not unique
                if (!unique_wifi_ssids.contains(current_ssid)) {
                    unique_wifi_ssids.add(current_ssid);
                }

                size--;
                adapter.notifyDataSetChanged();
            }
            wifiListHeader.setText("Select a WiFi network:");
            //displays the name of the selected airu device
            airuDisplay.setText("Selected AirU device: " + airu_result.SSID);
        } catch (Exception e) {
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
