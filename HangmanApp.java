package FinalProject;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanApp {
	/*
	 1. Represent attributes of the game.
	 1.1. Pick words user must guess;
	 1.2. Word found, show the progression of user;
	 1.3. Number of lives/errors. How many errors (MAX) made by the user in guessing game;
	 1.4. List of letters user have entered;
	 2. Choose the word to guess.
	 3. Start new game (method). When start a new game:
	 3.1. Number of errors/lives set to zero;
	 3.2. Clearing list of letters entered by the user;
	 3.3. Let computer pick new word;
	 3.4. Initialize the word have found array, shows the progression of the user. 
	 Letter to guess are represented with the ‘_’ character (NewGame method of the HangmanApp class);
	 4. Manage the word found by the user. 
	 New method for word that needs to be found and words that has been found.
	 5.Method for determining, if the word has been found.
	 6.Play method, with users interaction:
	 6.1. Lives/errors;
	 6.2. Letter from user by Scanner;
	 6.3.If word found "Won" message, if no, display lives left;
	 6.4. If user reaches max lives and haven't guessed the word, display "Lost" message.
	 
	 */

			
				//First introduction
				{ System.out.println(" Hangman Game by Katrîna.");
				System.out.println(" Hello, welcome to the game!");
				System.out.println("\n Guess hidden words by guessing letters, until you are out of lives."
				+ "\n You have 7 lives." 
				+ "\n Lets see how you do!"
				+ "\n Good luck!"
				+ "\n--------------------------------------------");//Introduction
					
				 }//end introduction
	
	
		//Game
		//Define 20 secret words that users will guess, class variables
		public static final String[] WORDS = { "ACCENTURE", "JAVA", "GAME", 
				"TEST", "LESSONS", "PROJECT", "CLASS", "ARRAY",
				"CHAR", "BOOLEAN", "LOOP", "PACKAGE", "PROGRAMMING",
				"STRING", "ECLIPSE", "GIT", "LAB", "NETWORK", "CATCH", "SYSTEM"};

		
		 public static final Random RANDOM = new Random();
		 //Max lives before user loses
		 public static final int MAX_LIVES = 8; //Number of errors/lives
		 //Word that needs to be found
		 private String wordHaveToFind;
		 //Word have been found, have been stored in a char array (to show progression of user)
		 private char[] wordHaveFound;
		 private int numbLives; //number of errors/lives
		 //Letters already entered by user
		 private ArrayList < String > letters = new ArrayList < > ();

		 //Method returning randomly next word to find
		 private String nextWordToFind() {
		  return WORDS[RANDOM.nextInt(WORDS.length)];
		 }

		 //Method for starting a new game
		 public void newGame() {
		  numbLives = 0;
		  letters.clear();
		  wordHaveToFind = nextWordToFind();

		  //"Word have been found" initialization
		  wordHaveFound = new char[wordHaveToFind.length()];

		  for (int i = 0; i < wordHaveFound.length; i++) {
		   wordHaveFound[i] = '_';
		  }
		 }

		 // Method returning "trues" if word is found by user
		 public boolean wordHaveFound() {
		  return wordHaveToFind.contentEquals(new String(wordHaveFound));
		 }

		 //Method updating the word found after user entered a character
		 private void enter(String c) {
		  //Update only if "C" has not already been entered
		  if (!letters.contains(c)) {
		   //Check if word to find contains "C"
		   if (wordHaveToFind.contains(c)) {
		    //If so, replace "_" by the character "C"
		    int index = wordHaveToFind.indexOf(c);

		    while (index >= 0) {
		     wordHaveFound[index] = c.charAt(0);
		     index = wordHaveToFind.indexOf(c, index + 1);
		    }
		   } else {
		    //"C" not in the word => error/lives lost
		    numbLives++;
		   }

		   //"C" is now a letter entered
		   letters.add(c);
		  }
		 }

		 //Method returning the state of the word found by the user until by now
		 private String wordFoundContent() {
		  StringBuilder builder = new StringBuilder();

		  for (int i = 0; i < wordHaveFound.length; i++) {
		   builder.append(wordHaveFound[i]);

		   if (i < wordHaveFound.length - 1) {
		    builder.append(" ");
		   }
		  }

		  return builder.toString();
		 }

		 //Play method for Hangman Game
		 public void play() {
		  try (Scanner input = new Scanner(System.in)) {
		   //Play while numbLives is lower than max errors/lives or user has found the word
		   while (numbLives < MAX_LIVES) {
		    System.out.println(" Enter a letter : ");
		    //Get next input from user
		    String str = input.next();

		    //Keep just first letter
		    if (str.length() > 1) {
		     str = str.substring(0, 1);
		    }

		    //Update word found
		    enter(str);

		    //Display current state
		    System.out.println(" " + wordFoundContent());

		    //Check if word is found
		    if (wordHaveFound()) {
		     System.out.println(" Congrats! You won!");
		     break;
		    } else {
		     //Display numb of tries remaining for the user
		     System.out.println(" => Remaining lives: " + (MAX_LIVES - numbLives));
		    }
		   }

		   if (numbLives == MAX_LIVES) {
		    //User lost
		    System.out.println(" Sorry, but you lost!");
		    System.out.println("=> Word you had to guess was : " + wordHaveToFind);
		   }
		  }
		 }
		 //Define a main entry point
		 public static void main(String[] args) {
				
			  HangmanApp hangmanGame = new HangmanApp();
			  hangmanGame.newGame();
			  hangmanGame.play();
			  
		 }//end main

	}//end class
