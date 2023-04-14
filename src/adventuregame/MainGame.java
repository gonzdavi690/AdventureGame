package adventuregame;


import java.util.ArrayList;
import java.util.HashMap;

/* A skeleton program for a text adventure game */
/* some other parts, like rooms, will be explained in class */

public class MainGame {

	static int INVSIZE = 10; //size of inventory	

	//instance variables
	HashMap<String,Room> roomMap = new HashMap<String,Room>();
	HashMap<String, Item> itemMap = new HashMap<String,Item>(); //list of all item objects
	//These hashmaps could also be in the Room and Item class if it's more convenient to create them there. They would have to be static though.

	//the inventory could be an array
	ArrayList<String> inventory = new ArrayList<String>();
	String currentRoom;
	Player player = new Player();

	int turns = 0;

	public static void main(String[]args){
		new MainGame();
	}

	MainGame() {

		boolean playing = true;
		String command = "";

		setup(); //create all objects needed, including map; print intro. message

		lookAtRoom(); //display information about the current room

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

		Room.setupRooms(roomMap);
		currentRoom = "Dirtmouth";

		//this part sets up all of the items in the game
		Item key = new Item("key");
		key.descr = "Simple key that can be used to open a door";

		Item door = new Item("key");
		door.descr = "A common door made of strong material. It has a lock that fits a simple key.";
		door.isCarryable = false;

		Item shinyRock = new Item("rock");
		shinyRock.descr = "A strange shiny rock... seems like it can be broken";
		shinyRock.isCarryable = false;
		shinyRock.healthPoints = 1;
		shinyRock.isAttackable = true;

		Item nail = new Item("nail");
		nail.descr = "A traditional weapon of Hallownest. Its blade is blunt with age and wear.";
		nail.damage = 1;

		Item cloak = new Item("cloak");
		cloak.descr = "A cloak that grants the user the power to dash a short distance forward.";
		cloak.isCarryable = true;

		Item fungi = new Item("fungi");
		fungi.descr = "Glowing fungi that seem yummy. You should try it.";
		fungi.isCarryable = true;
		fungi.isActivated = false;

		Item sign = new Item("sign");
		sign.descr = "Nothing special about it. Just a regular sign warning explorers of dangers beneath. ";
		sign.isCarryable = false;

		Item supplies = new Item ("supplies");
		supplies.descr = "Some scattered supplies from previous explorers. Among them there is a flashlght. ";
		//!TODO if the player inputs take supplies, say "you have to be more specific, which supply do you want to take?"

		Item flashlight = new Item("flashlight");
		flashlight.descr = "A common flashlight that allows you to see in the dark";
		flashlight.isCarryable = true;
		flashlight.isActivated = false;

		Item dreamNail = new Item("dreamnail");
		dreamNail.descr = "Allows the wielder to cut through the veil between dreams and waking. Can be used to reveal hidden dreams or open gateways";
		dreamNail.isCarryable = true;
		
		Item lurien = new Item ("lurien the watcher");
		lurien.descr = "Lurien the watcher one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight selaed in its vessel.";
		lurien.isCarryable = false;
		
		Item monomon = new Item ("monomon the teacher");
		monomon.descr = "Monomon the teacher is one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight selaed in its vessel.";
		monomon.isCarryable = false;
		
		Item herrah = new Item ("herrah the beast");
		herrah.descr = "Herrah the beast is one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight selaed in its vessel.";
		herrah.isCarryable = false;
		
		Item blackEgg = new Item ("black egg");
		blackEgg.descr = "A large black egg, looks like something mysterious lies inside. "
				+ "It has a large crack, but it is sealed by three mysterious beings through a subconscious force";
		blackEgg.isCarryable = false;


		itemMap.put("key", key);
		itemMap.put("door", door);
		itemMap.put("rock", shinyRock);
		itemMap.put("nail", nail);
		itemMap.put("cloak", cloak);
		itemMap.put("fungi", fungi);
		itemMap.put("sign", sign);
		itemMap.put("supplies", supplies);
		itemMap.put("flashlight", flashlight);
		itemMap.put("lurien the watcher", lurien);
		itemMap.put("monomon the teacher", monomon);
		itemMap.put("herrah the beast", herrah);
		itemMap.put("black egg", blackEgg);

		roomMap.get("Sly's shop").itemList.add(key);
		roomMap.get("Forgotten Crossroads").itemList.add(door);
		roomMap.get("Forgotten Crossroads").itemList.add(shinyRock);
		roomMap.get("Greenpath").itemList.add(cloak);
		roomMap.get("Fungal Wastes").itemList.add(fungi);
		roomMap.get("Fungal Wastes").itemList.add(sign);
		roomMap.get("City of Tears").itemList.add(supplies);
		roomMap.get("City of Tears").itemList.add(flashlight);
		roomMap.get("City of Tears").itemList.add(lurien);
		roomMap.get("Resting Grounds").itemList.add(dreamNail);
		roomMap.get("Resting Grounds").itemList.add(monomon);
		roomMap.get("Deepnest").itemList.add(herrah);
		roomMap.get("Temple Of The Black Egg").itemList.add(blackEgg);

		System.out.println("Greetings, player! Welcome to Hollow Knight. You will have to navigate through the terrain starting from dirtmouth. You will find that I've put a little parting gift in your inventory, you should go check it out. Anyway, I won't keep you here any longer traveller, but you can call on me anytime whenever you need 'help' >:)\n");


		inventory.add("nail");

	}


	java.util.Scanner sc = new java.util.Scanner(System.in);
	String getCommand() {
		System.out.print("=> ");		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		return text;
	}


	String preProcess(String text) {

		text = text.toLowerCase().trim();

		text = text.replaceAll(" into ", " in ");
		text = text.replaceAll(" rocks", " rock");
		text = text.replaceAll("pick up", "pickup");
		text = text.replaceAll("look at", "lookat");
		text = text.replaceAll("climb up", "climbup");

		return text;
	}

	boolean parseCommand(String text) {

		//handle situation where no words entered ...

		String words[] = text.split(" ");

		//separate out into word1, word2, etc.
		String word1 = words[0];
		String word2 = "";
		if (words.length > 1) {
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
		case "pickup": case "take": 
			pickUpItem(word2);
			break;
		case "attack":
			attack(word2);
			break;
		case "help":
			printHelp();
			break;
		case "look":
			lookAtRoom();
			break;
		case "buy":
			buyObject(word2);
			break;

			/**** two word commands ****/		
		case "examine":
			examineObject(word2);
			break;
		case "eat":
			eatItem(word2);
			break;		

			/**** SPECIAL COMMANDS ****/
			// ...		

		default: 
			System.out.println("Sorry, I don't understand that command. If you need help, type 'help'");
		}
		return true;
	}	

	//tons of other methods go here ...	

	void buyObject(String object) {
		
		if (player.geo >= 100) {
			inventory.add(object);
		}
		System.out.println("Congratulations on your purchase, traveller. Here is your " + object);
		System.out.println("my balls are itchy");
	}

	void attack(String word2) {

		if (itemMap.get(word2).isAttackable == true) {
			itemMap.get(word2).healthPoints--;
		} 
		if (itemMap.get(word2).healthPoints == 0) {
			System.out.println("Wow! There was geo incrusted inside the rock! You gain +100 geo. This is a valuable mineral.");
			if (word2.equals("rock")) {
				player.geo = 100;
				inventory.add("100 geo");
			}
			itemMap.remove(word2);
		}

	}	

	void lookAtRoom() {

		System.out.println("\n== " + roomMap.get(currentRoom).getTitle() + " ==");
		System.out.println(roomMap.get(currentRoom).getDesc());
		
	}

	void moveToRoom(char direction) {

		String newRoom = roomMap.get(currentRoom).getExit(direction);

		if (newRoom.length() == 0) {
			System.out.println("You can't go there.");
			return;
		}
		currentRoom = newRoom;
		lookAtRoom();

	}

	void pickUpItem(String object) {

		if (itemMap.get(object).isCarryable) {
			inventory.add(object);
		}

	}

	void showInventory() {

		System.out.println("Current inventory:");
		System.out.println("-----------------");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).toString());
		}
	}

	void printHelp() {

		System.out.println("Here are all of your controls: \n-----------------------------");
		System.out.println("n/north - go north             s/south - go south");
		System.out.println("e/east - go east               w/west - go west");
		System.out.println("u - go up                      d - go down");
		System.out.println("i/inventory - shows inventory  pickup/take - pick up item in current location");
		System.out.println("read - read object (only applies to special objects with this property)");
		System.out.println("eat - eat item (only applies to special objects with this property)");

	}

	void examineObject(String objectRead) {

		System.out.println(itemMap.get(objectRead).getName());
		System.out.println(itemMap.get(objectRead).getDescr());

	}

	void eatItem(String itemRead) {



	}

}

