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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView textStatus;
    TextView airuDisplay;
    Button buttonScan;
    int size = 0;
    ScanResult airu_result;
    List<ScanResult> network_results = new List<ScanResult>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<ScanResult> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(ScanResult scanResult) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends ScanResult> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends ScanResult> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public ScanResult get(int index) {
            return null;
        }

        @Override
        public ScanResult set(int index, ScanResult element) {
            return null;
        }

        @Override
        public void add(int index, ScanResult element) {

        }

        @Override
        public ScanResult remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<ScanResult> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<ScanResult> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<ScanResult> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textStatus = (TextView) findViewById(R.id.textStatus);
        airuDisplay = (TextView) findViewById(R.id.airuDisplay);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
        lv = (ListView)findViewById(R.id.list);

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
        this.adapter = new SimpleAdapter(WiFiDemo.this, arraylist, R.layout.row, new String[] { ITEM_KEY }, new int[] { R.id.list_value });
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
    }

    public void onClick(View view)
    {
        arraylist.clear();
        wifi.startScan();

        Toast.makeText(this, "Scanning...." + size, Toast.LENGTH_SHORT).show();
        try
        {
            size = size - 1;
            ArrayList<String> unique_wifi_ssids = new ArrayList<String>();
            while (size >= 0)
            {
                String current_ssid = network_results.get(size).SSID;
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ITEM_KEY, current_ssid + "  " + network_results.get(size).capabilities);

                if(!network_results.get(size).SSID.contains("AirU-") && !unique_wifi_ssids.contains(current_ssid)) {
                    arraylist.add(item);
                }

                if(!unique_wifi_ssids.contains(current_ssid)){
                    unique_wifi_ssids.add(current_ssid);
                }

                size--;
                adapter.notifyDataSetChanged();
            }
            //display the name of the selected airu device
            airuDisplay.setText(airu_result.SSID);
        }
        catch (Exception e)
        { }
    }

    public static String getCurrentSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        return ssid;
    }
}
