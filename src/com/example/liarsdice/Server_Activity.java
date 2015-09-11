package com.example.liarsdice;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.graphics.Color;  
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.content.Context;
import android.bluetooth.*;
import java.util.Set;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.BroadcastReceiver;



public class Server_Activity extends Activity {

    private ArrayAdapter<String> mArrayAdapter;
	private static final int REQUEST_ENABLE_BT = 1;
    private Set<BluetoothDevice> pairedDevices;
    private ListView myListView;
    BroadcastReceiver mReceiver;
@Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_main);	
	 myListView = (ListView)findViewById(R.id.listView1);

	 Log.i("Server_Activity", "FIRST");

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	Log.i("Server_Activity", "SECOND");

	
	if (mBluetoothAdapter == null) {
		Toast toast = Toast.makeText(getApplicationContext(), "Your device doesn't support bluethoot", 5);
		toast.show();
		finish();
	}
	
	 Log.i("Server_Activity", "THIRD");

	if (!mBluetoothAdapter.isEnabled()) {
	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
	}
	
	mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
	myListView.setAdapter(mArrayAdapter);
	
	pairedDevices = mBluetoothAdapter.getBondedDevices();
	// If there are paired devices
	if (pairedDevices.size() > 0) {
	    // Loop through paired devices
	    for (BluetoothDevice device : pairedDevices) {
	        // Add the name and address to an array adapter to show in a ListView
	        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	    }
	}
	
	
	// Create a BroadcastReceiver for ACTION_FOUND
	mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	        }
	    }
	};
	
	
	// Register the BroadcastReceiver
	IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy

}
	
@Override
protected void onDestroy() {
	super.onDestroy();
    unregisterReceiver(mReceiver);
}

}
