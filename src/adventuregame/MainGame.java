package adventuregame;


import java.util.ArrayList;
import java.util.HashMap;

/* A skeleton program for a text adventure game */
/* some other parts, like rooms, will be explained in class */

public class MainGame {

	static int INVSIZE = 10; //size of inventory	

	//instance variables
	HashMap<String,Room> roomList = new HashMap<String,Room>();
	HashMap<String, Item> itemList = new HashMap<String,Item>(); //list of all item objects
	//These hashmaps could also be in the Room and Item class if it's more convenient to create them there. They would have to be static though.

	//the inventory could be an array
	ArrayList<String> inventory = new ArrayList<String>();
	String currentRoom;
	Player player;

	int turns = 0;

	public static void main(String[]args){
		System.out.println("Kareen wuz  here");
		new MainGame();
	}

	MainGame() {

		boolean playing = true;
		String command = "";

		setup(); //create all objects needed, including map; print intro. message

		lookAtRoom(true); //display information about the current room

		/***** MAIN GAME LOOP *****/
		while (playing) {

			command = getCommand();

			playing = parseCommand(command);

			//check to see if player has died (in whichever various ways the player can die)

			//check to see if the player has won the game

		}

		// does anything need to be done after the main game loop exits?

	}

	void setup() {
		Room.setupRooms(roomList);
		// ... more stuff ...
		currentRoom = "clearing";
	}


	java.util.Scanner sc = new java.util.Scanner(System.in);
	String getCommand() {
		System.out.print("=> ");		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		return text;
	}


	String preProcess(String text) {
		//Step1.
		text = text.toLowerCase().trim();

		//Step1. word replacement
		text = text.replaceAll(" into ", " in ");
		text = text.replaceAll(" rocks", " rock");
		text = text.replaceAll("pick up", "pickup");
		text = text.replaceAll("look at", "lookat");
		text = text.replaceAll("climb up", "climbup");

		//Step3. remove "the" and "a" and "an"
		// ...
		
		return text;
	}

	boolean parseCommand(String text) {
		
		//handle situation where no words entered ...

		String words[] = text.split(" ");

		//separate out into word1, word2, etc.
		String word1 = words[0];
		String word2 = "";
		if (words.length>1) {
			word2 = words[1];
		} 
		  //But we have to make sure that this array element exists

		/***** MAIN PROCESSING *****/
		switch(word1) {

		/**** one word commands ****/
		case "quit":
		case "exit":
			System.out.print("Do you really want to quit the game? ");
			String ans = getCommand().toUpperCase();
			if (ans.equals("YES") || ans.equals("Y")) {
				System.out.print("Thanks for playing. Bye.");
				return false;
			}			
		case "n": case "s": case "w": case "e": case "u": case "d":
		case "north": case "south": case "west": case "east": case "up": case "down":
			moveToRoom(word1.charAt(0));
			break;
		case "i": case "inventory":
			showInventory();
			break;
		case "sleep":
			sleep();			
			break;	
		case "help":
			printHelp();
			break;

		/**** two word commands ****/		
		case "read":
			readObject(word2);
			break;
		case "eat":
			eatItem(word2);
			break;		

		/**** SPECIAL COMMANDS ****/
			// ...		

		default: 
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}	

	//tons of other methods go here ...	
	
	void lookAtRoom(boolean playing) {
		
	}
	
	void moveToRoom(char direction) {
		
	}
	
	void showInventory() {
		
	}
	
	void sleep() {
		
	}
	
	void printHelp() {
		
	}
	
	void readObject(String objectRead) {
		
	}
	
	void eatItem(String itemRead) {
		
	}

}

  