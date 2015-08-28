package com.example.liarsdice;

import android.util.Log;

public class Game_C {

	int Dices_Total;
	int Dices_Number[] = new int[6];
	
	//Jogada
	int Dice; 
	int Number;
	int Type;
	Players Player; 
	
	public void Bet(Players players[],int player_ID, int type, int dice, int number,int number_of_dices){
		Log.i("GAME_C","Entered Game_C");
		int dices = Count_DicesX(players,dice,number);
		Log.i("GAME_C","Entered with type "+type+" dices "+ dices);
		Dices_Number[dice] = dices;
		Log.i("GAME_C","Entered with type"+type);
		Dices_Total = number_of_dices;
	
		if (type == 1){
			Log.i("GAME_C","Entered if1");
			if (Dices_Number[dice-1] == number){
				Player.Remove_Dice(1);
			} else
				players[player_ID].Remove_Dice(1);
		}
		else if(type == 2){
			Log.i("GAME_C","Entered if2");
			Dice = dice;
			Number = number;
			Type = type;
			Player = players[player_ID];
		}
		else if (type == 3){
			Log.i("GAME_C","Entered if3");
			if (Dices_Number[dice-1] <= number){
				Player.Remove_Dice(1); 
			} else
				players[player_ID].Remove_Dice(1);
			
		}
	}
	protected int Count_DicesX(Players players[],int number_to_check,int number_of_dices){
		Log.i("GAME_C","Entered Count_DicesX");
		int count = 0;
		for (int i = 0; i < players.length;i++){
			for(int j = 0; j < players[i].Get_Dice_Values().length; j++){
				if (players[i].Get_Dice_Values()[j] == number_to_check)
					count++;
			}
		}
		Log.i("GAME_C","Finished Count_DicesX"+count);

		return count;
	}
}