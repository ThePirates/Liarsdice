package com.example.liarsdice;

import java.util.Random;


public class Players {
	int MAX = 6;
	int dice_number;
	int dice_values[]; 
	String name;
	int color;
	int pontuaction; 
	
	
	public Players(String nam, int number,int color1){
		name = nam;
		dice_number = number;
		color = color1;
	}
	
	public int random(){
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((MAX - 1) + 1) + 1;

	    return randomNum;
	}
	
	public void Shuffle_Dices(int dice_number){
		dice_values = new int[dice_number];
		for(int i = 0; i < dice_number; i++){
			dice_values[i] = random();
		} 
	}
	
	public int[] Get_Dice_Values(){
		return dice_values;
	}
	
	public void Remove_Dice(int a){
		dice_number = dice_number - a;
	}
	
	public void Insert_Dice(int a){
		dice_number = dice_number + a;
	}
	
	public void Pontuaction(int p){
		pontuaction += p;
	}
	
	public String Get_Name(){
		return name;
	}
	
}
