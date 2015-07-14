package com.example.liarsdice;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class MainActivity extends Activity {
	public final static String NUMBER_PLAYERS = "com.example.liarsdice.PLAYERSNUMBER";
	private final static String TAG = "Activity";
	private SeekBar players_bar;
	private TextView players_number_view; 
	private Button start_game;
	int players_number = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		Initialize_Variables();
		
		players_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	        
	           
	      @Override
	      public void onProgressChanged(SeekBar seekbar, int progresValue, boolean fromUser) {
	           players_number = progresValue;
	           players_number_view.setText("Number of players:" + players_number);
	        }
	     
	      @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
	    	   Log.i("SEEKBAR", "START");
          }
          
          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
        	  
        	  Log.i("SEEKBAR","STOP");
          }
       });
		
		Button start_game = (Button) findViewById(R.id.startgame);
		start_game.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Game.class);
				String number = players_number_view.getText().toString();
				intent.putExtra(NUMBER_PLAYERS, number);
				startActivity(intent);
			}		
		});

	}

	
	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "Entered the onResume() method");

	}

	@Override
	public void onPause() {
		super.onPause();

		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		Log.i(TAG, "Entered the onRestart() method");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.i(TAG, "Entered the onDestroy() method");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void Initialize_Variables(){
		players_bar = (SeekBar) findViewById(R.id.playersbar);
		players_number_view = (TextView) findViewById(R.id.players);
		players_number_view.setText("Number of players: 0");
		start_game = (Button) findViewById(R.id.startgame);
	}
}
