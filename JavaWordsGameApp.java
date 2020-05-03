/*
JavaWordsGameApp.java
C Miranda
28 4 2020
*/

import javax.swing.*;
public class JavaWordsGameApp{
    public static void main(String args[]){

		//variable
		int P1NOLIVES;
		int P2NOLIVES;

		//objects
		JavaWordsGame myJavaWordsGame = new JavaWordsGame();

		//input
		//rules of the game
 		JOptionPane.showMessageDialog(null, "Welcome to the Java Words Game!" + "\nThe Game rules are:" + "\n1- The game will be played with two player," + "\nand each player will be given a number of lives," + "\nWins the game who has lives left at the end of the game." + "\n2- You start the game with 0 points," + "\nbut you get points for the number of characters in the word you provide," + "\nwith some exclusion like the letters a, e and o." + "\n3- At the beginning of the game you will be given a letter," + "\nyou need to say a word that starts with that letter." + "\nThe next player needs to say a word," + "\nthat starts with the last 2 letters from the previous player." + "\n4- The word provided by each player can not be shorter than 3 characthers." + "\n5- Each entered word will be validated to make sure they are real words." + "\n6- The round ends when one of the players can not provide any word." + "\n7- The winner of the round starts the next round." + "\n8- If you don't know a word you can press the key dash (-)."+"\nPress stop to end the games.");
		myJavaWordsGame.noGames();//run the method that asks from the No of Games the player wants to play

		P1NOLIVES=myJavaWordsGame.getP1NOLIVES();//collect the information regards the no of lives each player will have
		P2NOLIVES=myJavaWordsGame.getP2NOLIVES();
		JOptionPane.showMessageDialog(null, "Player 1 receives: "+P1NOLIVES+"\nPlayer 2 receives: "+P2NOLIVES);

		//process and output
		//run the game
		myJavaWordsGame.mainGame1();

    }
}