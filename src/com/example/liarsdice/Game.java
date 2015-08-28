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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class Game extends Activity {
	public final static String NUMBER_PLAYERS = "com.example.liarsdice.PLAYERSNUMBER";
	private final static String TAG = "GAME";
	String players_number;
	String dices_string; 
	int number_of_players;
	private TextView players_number_view; 	
	RelativeLayout relative;
	 private RadioGroup radioGroup;
	 int dice_bet; 
	 int dice_number_bet;
	 private SeekBar dice_number_bar;
	 TextView Text_Bet;
	 int NUMBER_OF_DICES = 3;
	 int COLOR = 1;
	 int dices_many= 0;
	 Game_C game;
	 Players players_list[];
	 TextView players_screen_dice[];
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
       players_screen_dice = new TextView[number_of_players];
       players_list = new Players[number_of_players];
       
       Log.i(TAG, "Before the cycle");
        
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);  
        
       LinearLayout lL = new LinearLayout(this);
       lL.setId(900);
       LinearLayout mm = new LinearLayout(this);
       lL.setOrientation(LinearLayout.HORIZONTAL);
       mm.setOrientation(LinearLayout.HORIZONTAL);
       
       for (int i = 0; i < number_of_players; i++){
        players_list[i] = new Players("Player "+i,NUMBER_OF_DICES,COLOR);
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
       update_dice_string();

        
       
      
       radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

       radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rbutton1) {
                    Toast.makeText(getApplicationContext(), "choice: 1",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 1;
                } else if(checkedId == R.id.rbutton2) {
                    Toast.makeText(getApplicationContext(), "choice: 2",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 2;
                } else if(checkedId == R.id.rbutton3) {
                    Toast.makeText(getApplicationContext(), "choice: 3",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 3;
                } else if(checkedId == R.id.rbutton4) {
                    Toast.makeText(getApplicationContext(), "choice: 4",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 4;
                } else if(checkedId == R.id.rbutton5) {
                    Toast.makeText(getApplicationContext(), "choice: 5",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 5;
                } else if(checkedId == R.id.rbutton6) {
                    Toast.makeText(getApplicationContext(), "choice: 6",
                            Toast.LENGTH_SHORT).show();
                    dice_bet = 6;
                } else {
                    Toast.makeText(getApplicationContext(), "choice: Vibration",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });  
    
       //Contar os dados em jogo
       Count_Dices();
       
       dice_number_bar = (SeekBar) findViewById(R.id.dice_number);
       dice_number_bar.setMax(dices_many);
       dice_number_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	   
 	      @Override
 	      public void onProgressChanged(SeekBar seekbar, int progresValue, boolean fromUser) {
 	           dice_number_bet = progresValue;
 	           Log.i("Game", "dice_bet: "+dice_number_bet);
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
       
    game = new Game_C();

	Button Exactly = (Button) findViewById(R.id.Bet_Exactly);
	Text_Bet = (TextView) findViewById(R.id.textView1);
	Exactly.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Count_Dices();
			Text_Bet.setText("The player ME bet that there are exaclty "+dice_number_bet+" dices with the value "+dice_bet);
			game.Bet(players_list,0,1,dice_bet,dice_number_bet,dices_many);
			update_dice_string();
		}		
	});

	Button More_Than = (Button) findViewById(R.id.Bet_Plus);
	More_Than.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Count_Dices();
			Text_Bet.setText("The player ME bet that there are more than "+dice_number_bet+" dices with the value "+dice_bet);
			Log.i("GAME", " More than Button Clicked");
			game.Bet(players_list,0,2,dice_bet,dice_number_bet,dices_many);
			update_dice_string();
		}		
	});
	
	Button Liar = (Button) findViewById(R.id.Bet_Liar);
	Liar.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Count_Dices();
			Text_Bet.setText("The player ME bet that the player "+"player before "+"is a liar");
			game.Bet(players_list,0,3,dice_bet,dice_number_bet,dices_many);
			update_dice_string();
		}		
	});
	
	
	}

	Players[] Start_Game(Players[] players, int number_players){
		for(int i = 0; i < number_players;i++){
			players[i].Shuffle_Dices();
		}
		return players; 
	}
	
	


    protected void update_dice_string(){
    Log.i("Game.java", "Entered update_dice_string()");
 	   dices_string = " ";
        for (int i = 0; i < number_of_players; i++){
      	   dices_string = " ";
            for (int j = 0; j < players_list[i].Get_Dice_Values().length; j++){
         	   dices_string += players_list[i].Get_Dice_Values()[j];   
            }
            dices_string += " ";
     	   players_screen_dice[i].setText(dices_string);
        }
    }

    void Count_Dices(){
        dices_many = 0;
        for(int i = 0; i < number_of_players;i++){
     	   dices_many += players_list[i].Get_Dice_Number();
        }
    }
	
	void Liar(){
		
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