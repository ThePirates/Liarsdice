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

	    int randomNum = rand.nextInt((MAX - 1) + 1) + 1;

	    return randomNum;
	}
	
	public void Shuffle_Dices(){
		dice_values = new int[dice_number];
		for(int i = 0; i < dice_number; i++){
			dice_values[i] = random();
		} 
	}
	
	public int[] Get_Dice_Values(){
		return dice_values;
	}
	
	public void Remove_Dice(int a){
		if (dice_number > 0){
			dice_number = dice_number - a;
			dice_values[dice_number] = 0;
		}
	}
	
	public void Insert_Dice(int a){
		dice_number = dice_number + a;
	}
	
	public void Pontuaction(int p){
		pontuaction += p;
	}

	public int Get_Dice_Number(){
		return dice_number;
	}
	
	public String Get_Name(){
		return name;
	}
	
}
