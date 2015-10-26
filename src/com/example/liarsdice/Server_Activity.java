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
import java.util.UUID;
import java.io.IOException;
import java.lang.Thread;



public class Server_Activity extends Activity {

    private ArrayAdapter<String> mArrayAdapter;
	private static final int REQUEST_ENABLE_BT = 1;
    private Set<BluetoothDevice> pairedDevices;
    BluetoothAdapter mBluetoothAdapter;
    private ListView myListView;
    BroadcastReceiver mReceiver;
    private BluetoothServerSocket mmServerSocket;
    private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    
@Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_main);	
	 
	 myListView = (ListView)findViewById(R.id.listView1);

	 Log.i("Server_Activity", "FIRST");

	mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	Log.i("Server_Activity", "SECOND");

	
	if (mBluetoothAdapter == null) {
		Toast toast = Toast.makeText(getApplicationContext(), "Your device doesn't support bluethoot", 5);
		toast.show();
		Log.i("Server_Activity","SECOND_HALF");
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
		mArrayAdapter.add("List of Paired Devices");
	    for (BluetoothDevice device : pairedDevices) {
	        // Add the name and address to an array adapter to show in a ListView
	        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	    }
	}
	
    mArrayAdapter.add("List of Discovered Devices");

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
	
	while(mmServerSocket==null);
	AcceptThread();
	run();
}
	
public void AcceptThread() {
    BluetoothServerSocket tmp = null;
      try {
          tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("MYYAPP", MY_UUID_SECURE);

      } catch (IOException e) { 
    	  Log.e("Server_Activity","listen() nieudany",e);
      }
      mmServerSocket = tmp;
   }

public void run() {
    BluetoothSocket socket = null;
    while (true) {
        try {
            socket = mmServerSocket.accept();
            Log.i("Server_Activity","listening");
        } catch (IOException e) {
            break;
        }
        if (socket != null) {
            Log.i("Server_Activity","doneeeee");
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }
} 

@Override
protected void onDestroy() {
	super.onDestroy();
    unregisterReceiver(mReceiver);
}

}


private class AcceptThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;
 
    public AcceptThread() {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }
 
    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)
                manageConnectedSocket(socket);
                mmServerSocket.close();
                break;
            }
        }
    }
 
    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
}
