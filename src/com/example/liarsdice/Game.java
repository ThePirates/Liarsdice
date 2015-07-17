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
import android.widget.RelativeLayout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.graphics.Color;  


public class Game extends Activity {
	public final static String NUMBER_PLAYERS = "com.example.liarsdice.PLAYERSNUMBER";
	private final static String TAG = "GAME";
	String players_number;
	int number_of_players;
	private TextView players_number_view; 	
	RelativeLayout relative;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TAG", "Entered onCreate()");
		setContentView(R.layout.game_l);
        relative =  (RelativeLayout) findViewById(R.id.board);

		Intent intent = getIntent();
		
		players_number = intent.getStringExtra(MainActivity.NUMBER_PLAYERS);
		//players_number_view = (TextView) findViewById(R.id.players);
		Log.i(TAG, "Before the problem");
		//players_number_view.setText("Number of players:" + players_number);
		Log.i("SHOW_TEXT", players_number);
		number_of_players = Integer.parseInt(players_number);
		
        // Creating the players and its TextViews;
       TextView players_screen_name[] = new TextView[number_of_players];
       TextView players_screen_dice[] = new TextView[number_of_players];
       Players players_list[] = new Players[number_of_players];
       
       Log.i(TAG, "Before the cycle");
        
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);  
        
       LinearLayout lL = new LinearLayout(this);
       lL.setId(900);
       LinearLayout mm = new LinearLayout(this);
       lL.setOrientation(LinearLayout.HORIZONTAL);
       mm.setOrientation(LinearLayout.HORIZONTAL);
       
       for (int i = 0; i < number_of_players; i++){
        players_list[i] = new Players("nome   ",3,1);
        players_screen_name[i] = new TextView(this);
        players_screen_name[i].setId(i);
        players_screen_name[i].setText(players_list[i].Get_Name());
        
 
       
        
        players_screen_dice[i] = new TextView(this);
        players_screen_dice[i].setId(100+i);
        players_screen_dice[i].setText("XXX");
        
        if( i > 0){
            params.addRule(RelativeLayout.RIGHT_OF,i-1); 
            }
    
        lL.addView(players_screen_name[i]);
        
        mm.addView(players_screen_dice[i]);
        }
       
       
       relative.addView(lL);
       
       RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
       param.addRule(RelativeLayout.BELOW,900); 
       relative.addView(mm,param);
       
       Start_Game(players_list,number_of_players);
       
       for (int i = 0; i < number_of_players; i++){
    	   players_screen_dice[i].setText(""+players_list[i].Get_Dice_Values()[0]+" "+players_list[i].Get_Dice_Values()[1]+" "+players_list[i].Get_Dice_Values()[2]+"  ");
       }
       
    }

	Players[] Start_Game(Players[] players, int number_players){
		for(int i = 0; i < number_players;i++){
			players[i].Shuffle_Dices();
		}
		return players; 
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
	
}