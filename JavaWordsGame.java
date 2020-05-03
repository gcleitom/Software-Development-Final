/*
JavaWordsGame.java
C Miranda
28 4 2020
*/

import java.util.*;
import javax.swing.*;
public class JavaWordsGame{

	//members data
	private int P1NOLIVES;//number of lives the players will receive
	private int P2NOLIVES;//number of lives the player 2 will receive
	private int noGames; //input number of games the player wants to play
	private Random randLetter;//generate random Letter;
	private char randChar;//Holds the random letter;
	private String player1W;//input Player 1 words
	private String player2W;//input Player 2 words
	private String playerPWord; //adds the recent word into this varibale to compare string in the next round
	private char firstWordLetter; //holds the first letter from the player input
	private String lastTwoLetters; // holds the last two letters from the player input
	private String firstTwoLetters; //holds the first two letters from the player input to be used in the next round
	private int p1Count;//process and output - count the points for the Player 1
	private int p2Count;//process and output - count the points for the Player 2
	private String[] arrayWords; //hold the value of the Vocabulary array from the LimitedVocabulary.java
	private List<Integer> arrayP1Points;//Creates an array with the result of each round for Player 1
	private List<Integer> arrayP2Points;//Creates an array with the result of each round for Player 2
	private List<Integer> arrayP1TotalPoints; //Hold the total result of the ArrayP1Points
	private List<Integer> arrayP2TotalPoints; //process and output - Hold the total result of the ArrayP2Points
	private boolean containWord; //Holds the boolean used for the method to find the word given by the user into the arrayWords
	private String winnerPerGame; //output - Holds which player won per game
	private String winner; // output - Holds which player won the overall game

    //constructor
    public JavaWordsGame(){
		P1NOLIVES=4;
		P2NOLIVES=4;
		noGames=0;
		randLetter=new Random();
		player1W="";
		player2W="";
		playerPWord="";
		firstWordLetter=' ';
		lastTwoLetters="";
		firstTwoLetters="";
		p1Count=0;
		p2Count=0;
		LimitedVocabulary myLimitedVocabulary=new LimitedVocabulary();
		arrayWords=myLimitedVocabulary.getArrayWords();
		arrayP1Points=new ArrayList<>();
		arrayP2Points=new ArrayList<>();
		arrayP1TotalPoints=new ArrayList<>();
		arrayP2TotalPoints=new ArrayList<>();
		containWord=false;
		winnerPerGame="";
		winner="";
    }

	//setter
	public void setPlayer1W(String player1W){
		this.player1W=player1W.toLowerCase();
	}

	public void setPlayer2W(String player2W){
		this.player2W=player2W;
	}

	//generate random letter
	public void randLetterGenerator(){
		randChar=(char)(randLetter.nextInt(26)+'a');
	}

	//run main game
	public void mainGame1(){
		do{
			player1Game();
			if(!player1W.equals("stop")){
				if(P1NOLIVES != 0){
					player2Game();
				}
			}
		}
		while(P1NOLIVES>0 && P2NOLIVES>0);
		afterMainGame();
	}

	public void mainGame2(){
		do{
			player2Game();
			if(!player2W.equals("-")){
				if(P2NOLIVES!=0){
					player1Game();
				}
			}
		}
		while(P1NOLIVES>0 && P2NOLIVES>0);
		afterMainGame();
	}

	//Runs after the user decides stop, or if the number of lives are gone, or if the number os games are one
	public void afterMainGame(){
		noGames--;//deduct a life from the user
		if(noGames!=0 && !player1W.equals("stop") && !player2W.equals("stop")){//runs if the user's number os lives are gone
			arrayP1TotalPoints.add(p1Count);
			arrayP2TotalPoints.add(p2Count);
			if(P1NOLIVES==0){//if the Player run out of lives
				P1NOLIVES=4;
				P2NOLIVES=4;
				playerPWord="";
				winnerPerGame();
				JOptionPane.showMessageDialog(null, "This Game result is:" + "\nPlayer 1: " + p1Count + "\nPlayer 2: " + p2Count + "\nThe Winner is: " + winnerPerGame);
				p1Count = 0;
				p2Count = 0;
				mainGame2();
            }
			else if(P2NOLIVES==0){//if the player 2 run out of lives
				P1NOLIVES=4;
				P2NOLIVES=4;
				playerPWord="";
				winnerPerGame();
				JOptionPane.showMessageDialog(null, "This Game result is:" + "\nPlayer 1: " + p1Count + "\nPlayer 2: " + p2Count + "\nThe Winner is: " + winnerPerGame);
				p1Count=0;
				p2Count=0;
				mainGame1();
            }
        }
		else{//when the player decide to stop the game or if the number of games are over.
			arrayP1TotalPoints.add(p1Count);//add the result of the round to the final arrive
			arrayP2TotalPoints.add(p2Count);
			int totalAP1TPoints=0;
			for(int i=0;i<arrayP1TotalPoints.size();i++){//run to calculate the amount of points stores in the array
				totalAP1TPoints=totalAP1TPoints+arrayP1TotalPoints.get(i);
			}
			int totalAP2TPoints=0;
			for(int j=0;j<arrayP2TotalPoints.size();j++){
				totalAP2TPoints=totalAP2TPoints+arrayP2TotalPoints.get(j);
			}
			if(totalAP1TPoints < totalAP2TPoints){//check who makes more points and give the result
				winner="PLAYER 2";
			}
			else{
				winner="PLAYER 1";
			}
			JOptionPane.showMessageDialog(null, "This Game result is:"+"\nPlayer 1: " + p1Count + "\nPlayer 2: "+p2Count+"\nThe Winner is: "+winnerPerGame+"\nPress Enter to See the overall Result.");
			JOptionPane.showMessageDialog(null, "What an amazing game!"+"\nThe TOTAL result for All the Games together is:"+"\nPlayer 1: "+totalAP1TPoints+"\nPlayer 2: "+totalAP2TPoints+"\nThe Winner is: "+winner);
			System.exit(0);
		}
	}

	//ask how many times would like to play the game and validate it
	public void noGames(){
		try{
		noGames=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter how many times would you like to play the game"));
		if(noGames<=0 || noGames>1000){//I added a limit of how many times the user can play the game
			noGames=0;
			noGames();
		}
		}
		catch(Exception e){
			noGames=0;
			noGames();
		}
	}

    //check who is the winner perGame
	public void winnerPerGame(){
		if(p1Count < p2Count){
			winnerPerGame="Player 2";
		}
		else{
			winnerPerGame="Player 1";
		}
	}

    //count the points per player right word
	public void pointP1Count(){
		for(int i=0;i<player1W.length();i++){
			if(player1W.charAt(i)!='a' && player1W.charAt(i)!='e' && player1W.charAt(i)!='o'){
				p1Count++;
			}
		}
		arrayP1Points.add(p1Count);
	}

	public void pointP2Count(){
		for(int i=0;i<player2W.length();i++){
			if(player2W.charAt(i)!='a' && player2W.charAt(i)!='e' && player2W.charAt(i)!='o'){
				p2Count++;
			}
		}
		arrayP2Points.add(p2Count);
	}

	//get the last two letters of the player word and keep for the next round/ it is also use to compare with the word from current round
	public void checkLastTwoLetter(){
		lastTwoLetters=Character.toString(playerPWord.charAt(playerPWord.length()-2))+Character.toString(playerPWord.charAt(playerPWord.length()-1));
	}

    //get the first two letters from Player 1 in the current round and compare with the letters from the previous words
	public void checkFirst2LettersP1(){
		firstTwoLetters=Character.toString(player1W.charAt(0))+Character.toString(player1W.charAt(1));
	}

    //get the first two letters from Player 2 in the current round and compare with the letters from the previous words
	public void checkFirst2LettersP2(){
		firstTwoLetters=Character.toString(player2W.charAt(0))+Character.toString(player2W.charAt(1));
	}

	//compute
	//play the game when the Player 1 starts
	public void player1Game(){
		if(playerPWord.length()<2){//if the previous word stored is smalled than 2 characters it means the there is no number so the game will start from this part
			randLetterGenerator();//generate the random letters for this turn
			playerPWord=Character.toString(randChar);//add random letter to the previous words variable
			player1W=JOptionPane.showInputDialog(null, "The First letter is: > "+randChar+" < Player 1 please enter the word or"+"\npress dash key '-' if you don't know a word:");
			player1W=player1W.toLowerCase();//convert the input to lower case
			if(!player1W.equals("stop")){//if the user presses stop the game ends
				if(!player1W.equals("")){//check if the input is empty
					if(!player1W.equals("-")){//if the player doesn't know the answer it passes to the next player and the amount of lives get reduced
						firstWordLetter=player1W.charAt(0);//get the user input and take the first character
						if(firstWordLetter==randChar){//check if the first character matchs with the previous given
							containWord=Arrays.asList(arrayWords).contains(player1W);//convert the array in list and check if the user input is within the values
							if(containWord){
								if(player1W.length()>=3){//validate if the input is longer than 3 characters
									pointP1Count();//calculate the points
									playerPWord=player1W;//pass the current input to the preivous variable
									JOptionPane.showMessageDialog(null, "The word "+player1W+" is valid!");//output message to the player
								}
								else{
									P1NOLIVES--;//reduces player lives
									playerPWord="";//set the variable back to empty
									JOptionPane.showMessageDialog(null, "The word "+player1W+" is smaller than 3 letters.\nTotal lives left: "+P1NOLIVES);//output message to the player
								}
							}
							else{
								P1NOLIVES--;
								playerPWord="";
								JOptionPane.showMessageDialog(null, "The word "+player1W+" is not a valid word from the list.\nTotal lives left: "+P1NOLIVES);
							}
						}
						else{
                            P1NOLIVES--;
                            playerPWord="";
                            JOptionPane.showMessageDialog(null, "The first letter from the word "+player1W+" doesn't match.\nTotal lives left: "+P1NOLIVES);
						}
					} else {
						P1NOLIVES--;
						playerPWord="";
						JOptionPane.showMessageDialog(null, "You don't know the word.\nTotal lives left: "+P1NOLIVES);
					}

				}
				else{
					P1NOLIVES--;
					playerPWord="";
					JOptionPane.showMessageDialog(null, "No value entered. Total lives left: "+P1NOLIVES);
				}
			}
			else{
				afterMainGame();//if the player decide to stop
			}
		}
		else if(playerPWord.length()>=2){//this part will check the if the previous word was longer than 2 characters, if it was, means that the game should start from this part
			checkLastTwoLetter();//run the method to get the two last letters
			player1W=JOptionPane.showInputDialog(null, "The letters are: > "+lastTwoLetters+" < Player 1 please enter the word or"+"\npress dash key '-' if you don't know a word:");
			player1W=player1W.toLowerCase();
			if(!player1W.equals("stop")){
				if(!player1W.equals("")){
					if(!player1W.equals("-")){
						checkFirst2LettersP1();//run the method to get the first two letters from the current word
						if(lastTwoLetters.equals(firstTwoLetters)){//check if the previous and current two letters match
							containWord=Arrays.asList(arrayWords).contains(player1W);
							if(containWord){
								if(player1W.length()>=3){
									pointP1Count();
									playerPWord=player1W;
									JOptionPane.showMessageDialog(null, "The word "+player1W+" is valid!");
								}
								else{
									P1NOLIVES--;
									playerPWord="";
									JOptionPane.showMessageDialog(null, "The word "+player1W+" is smaller than 3 letters.\nTotal lives left: "+P1NOLIVES);
								}
							}
							else{
								P1NOLIVES--;
								playerPWord="";
								JOptionPane.showMessageDialog(null, "The word "+player1W+" is not a valid word from the list.\nTotal lives left: "+P1NOLIVES);
							}
						}
						else{
							P1NOLIVES--;
							playerPWord="";
							JOptionPane.showMessageDialog(null, "The first two letters from the word "+player1W+" don't match.\nTotal lives left: "+P1NOLIVES);
						}
					}
					else{
						P1NOLIVES--;
						playerPWord="";
						JOptionPane.showMessageDialog(null, "You don't know the word, Total lives left: "+P1NOLIVES);
					}
				}
				else{
					P1NOLIVES--;
					playerPWord = "";
					JOptionPane.showMessageDialog(null, "No value entered. Total lives left: "+P1NOLIVES);
				}
			}
			else{
				afterMainGame();
			}
		}
	}

	//play the game when the Player 2 starts, the comments above also define the same funcionalities but for the player 2 this time
	public void player2Game(){
		if(playerPWord.length()<2){
			randLetterGenerator();
			playerPWord=Character.toString(randChar);
			player2W=JOptionPane.showInputDialog(null, "The First letter is: > "+randChar+" < Player 2 please the word or"+"\npress dash key '-' if you don't know a word:");
			player2W=player2W.toLowerCase();
			if(!player2W.equals("stop")){
				if(!player2W.equals("")) {
					if(!player2W.equals("-")){
						firstWordLetter = player2W.charAt(0);
						if(firstWordLetter==randChar){
							containWord=Arrays.asList(arrayWords).contains(player2W);
							if(containWord){
								if(player2W.length()>=3){
									pointP2Count();
									playerPWord=player2W;
									JOptionPane.showMessageDialog(null, "The word "+player2W+" is valid!");
								}
								else{
									P2NOLIVES--;
									playerPWord="";
									JOptionPane.showMessageDialog(null, "The word "+player2W+" is smaller than 3 letters.\nTotal lives left: "+P2NOLIVES);
								}
							}
							else{
								P2NOLIVES--;
								playerPWord = "";
								JOptionPane.showMessageDialog(null, "The word "+player2W+" is not a valid word from the list.\nTotal lives left: "+P2NOLIVES);
							}
						}
						else{
							P2NOLIVES--;
							playerPWord = "";
							JOptionPane.showMessageDialog(null, "The first letter from the word "+player2W+" doesn't match.\nTotal lives left: "+P2NOLIVES);
						}
					}
					else{
						P2NOLIVES--;
						playerPWord="";
						JOptionPane.showMessageDialog(null, "You don't know the word, Total lives left: "+P2NOLIVES);
					}
				}
				else{
					P2NOLIVES--;
					playerPWord="";
					JOptionPane.showMessageDialog(null, "No value entered. Total lives left: "+P2NOLIVES);
				}
			}
			else{
				afterMainGame();
			}
		}
		else if(playerPWord.length()>=2){
			checkLastTwoLetter();
			player2W=JOptionPane.showInputDialog(null, "The letters are: > "+lastTwoLetters+" < Player 2 please enter the word: ");
			player2W=player2W.toLowerCase();
			if(!player2W.equals("stop")){
				if(!player2W.equals("")){
					if(!player2W.equals("-")){
						checkFirst2LettersP2();
						if(lastTwoLetters.equals(firstTwoLetters)){
							containWord=Arrays.asList(arrayWords).contains(player2W);
							if(containWord){
								if(player2W.length()>=3){
									pointP2Count();
									playerPWord=player2W;
									JOptionPane.showMessageDialog(null, "The word "+player2W+" is valid!");
								}
								else{
									P2NOLIVES--;
									playerPWord="";
									JOptionPane.showMessageDialog(null, "The word "+player2W+" is smaller than 3 letters.\nTotal lives left: "+P2NOLIVES);
								}
							}
							else{
								P2NOLIVES--;
								playerPWord="";
								JOptionPane.showMessageDialog(null, "The word "+player2W+" is not a valid word from the list.\nTotal lives left: "+P2NOLIVES);
							}
						}
						else{
							P2NOLIVES--;
							playerPWord="";
							JOptionPane.showMessageDialog(null, "The first two letters from the word "+player2W+" don't match.\nTotal lives left: "+P2NOLIVES);
						}
					}
					else{
						P2NOLIVES--;
						playerPWord="";
						JOptionPane.showMessageDialog(null, "You don't know the word, Total lives left: "+P2NOLIVES);
					}
				}
				else{
					P2NOLIVES--;
					playerPWord="";
					JOptionPane.showMessageDialog(null, "No value entered. Total lives left: "+P2NOLIVES);
				}
			}
			else{
				afterMainGame();
			}
		}
	}

	//getter
	public int getP1NOLIVES(){
		return P1NOLIVES;
	}

	public int getP2NOLIVES(){
		return P2NOLIVES;
	}

}
