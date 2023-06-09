package adventuregame;

//FIXME once you die it thinks you are at spawn and currentroom at the same time.

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
	String spawn = "Dirtmouth";
	Player player = new Player();
	int dreamerSeals = 3;
	boolean ending = false;
	Boss hornet = new Boss(5,1);
	Boss hollowKnight = new Boss(8,1);

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

			if(!player.alive) {
				currentRoom = spawn;
				player.healthPoints = 3;
				player.alive = true;
				lookAtRoom();
			}

			if (ending) {
				System.out.println("As you land a final attack on The Hollow Knight, the infection which it had been carrying for eternity, slowly releases. To prevent the entire kingdom from destrcution, "
						+ "The Hollow Knight was trapped inside the black egg, for eternity, to retain that infection there. Now, it has been transfered to you. You helplessly watch as the chains that were holding the Hollow Knight start holding you instead."
						+ "Your eyes turn a bright orange color, and the black egg is re-sealed. You have become the new Hollow Knight.");
				System.out.println("Thank you for playing :)");
				System.out.println("Game by David and Karim");
				System.out.println("Creative inspiration: Hollow Knight. All credits for the design of the rooms, characters, and storyline goes to Team Cherry. ");
				playing = false;
			}

		}
		System.exit(0);

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
		cloak.descr = "A cloak that grants the user the power to dash a short distance forward, going over gaps. Type in the direction you want to go in and it will dash automatically. ";
		cloak.isCarryable = true;

		Item fungi = new Item("fungi");
		fungi.descr = "Glowing fungi that seem yummy. You should try it.";
		fungi.isCarryable = true;
		fungi.isActivated = false;

		Item sign = new Item("sign");
		sign.descr = "Nothing special about it. Just a regular sign warning explorers of dangers beneath.";
		sign.isCarryable = false;

		Item supplies = new Item ("supplies");
		supplies.descr = "Some scattered supplies from previous explorers. Among them there is a flashlght.";
		supplies.isCarryable = false;
		//!TODO if the player inputs take supplies, say "you have to be more specific, which supply do you want to take?"

		Item flashlight = new Item("flashlight");
		flashlight.descr = "A common flashlight that allows you to see in the dark";
		flashlight.isCarryable = true;
		flashlight.isActivated = false;

		Item dreamNail = new Item("dreamnail");
		dreamNail.descr = "Allows the wielder to cut through the veil between dreams and waking. Can be used to reveal hidden dreams or open gateways";
		dreamNail.isCarryable = true;

		Item lurien = new Item ("lurien");
		lurien.descr = "Lurien the watcher one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight sealed in its vessel.";
		lurien.isCarryable = false;

		Item monomon = new Item ("monomon");
		monomon.descr = "Monomon the teacher is one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight sealed in its vessel.";
		monomon.isCarryable = false;

		Item herrah = new Item ("herrah");
		herrah.descr = "Herrah the beast is one of the three dreamers: beings whose goal is to remain in eternal sleep and keep the Hollow Knight sealed in its vessel.";
		herrah.isCarryable = false;



		itemMap.put("key", key);
		itemMap.put("door", door);
		itemMap.put("rock", shinyRock);
		itemMap.put("nail", nail);
		itemMap.put("cloak", cloak);
		itemMap.put("fungi", fungi);
		itemMap.put("sign", sign);
		itemMap.put("supplies", supplies);
		itemMap.put("flashlight", flashlight);
		itemMap.put("lurien", lurien);
		itemMap.put("monomon", monomon);
		itemMap.put("dreamnail", dreamNail);
		itemMap.put("herrah", herrah);


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

		System.out.println("Greetings, player! Welcome to Hollow Knight. You will have to navigate through the terrain starting from dirtmouth. You will find that I've put a little parting gift in your inventory, you should go check it out. Anyway, I won't keep you here any longer traveller, but you can call on me anytime whenever you need 'help' >:)\n");
		System.out.println("TIP: Please activate word wrap on your console for all the text to show up.");

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

		String words[] = text.toLowerCase().split(" ");

		//separate out into word1, word2, etc.
		String word1 = words[0];
		String word2 = "";
		String word3 = "";

		if (words.length > 1) {
			word2 = words[1];
		} 

		if (words.length > 2) {
			word3 = words[2];
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
		case "help":
			printHelp();
			break;
		case "look":
			lookAtRoom();
			break;	
		case "sit":
			sitOnBench();
			break;

			/**** two word commands ****/	

		case "attack":
			attack(word2);
			break;
		case "eat":
			eatItem(word2);
			break;
		case "buy":
			buyObject(word2);
			break;	
		case "open":
			openDoor(word2);
			break;

			/**** Three word commands****/

		case "turn":
			if (word2.equals("off")) {
				turnOffItem(word3);
			}
			if (word2.equals("on")) {
				turnOnItem(word3);
			} else {
				System.out.println("Sorry, I don't understand that command. If you need help, type 'help'");
			}
			break;
		case "dream":
			if (word2.equals("nail")) {
				dreamNailItem(word3);
			} else {
				System.out.println("Sorry, I don't understand that command. If you need help, type 'help'");
			}
			break;
		case "take":
			if (word2.equals("dream")) {
				word2 = "dreamnail";
			}
			pickUpItem(word2);
			break;
		case "examine":
			if (word2.equals("dream")) {
				word2 = "dreamnail";
			}
			examineObject(word2);
			break;

			/**** SPECIAL COMMANDS ****/
			// ...		

		default: 
			System.out.println("Sorry, I don't understand that command. If you need help, type 'help'");
		}
		return true;
	}

	void buyObject(String object) {

		if (!currentRoom.equals("Sly's shop")) {
			System.out.println("You are not in a shop. ");
			return;
		} else {
			if (!object.equals("key")) {
				System.out.println("That isn't for sale at the moment. ");
			} else if (player.geo >= 100) {
				inventory.add(object);
				System.out.println("Congratulations on your purchase, traveller. Here is your " + object);
				player.geo -= 100;
				inventory.remove("100 geo");
			} else {
				System.out.println("You don't have enough geo to purchase that. Come back soon! ");
			}
		}
	}

	void sitOnBench() {

		if (currentRoom.equals("Dirtmouth") || currentRoom.equals("Greenpath") || currentRoom.equals("City of Tears") || currentRoom.equals("Temple Of The Black Egg") ) {
			System.out.println("A much needed rest, thank you! ");
			player.healthPoints =3;
			spawn = currentRoom;
			player.healthPoints = 3;
		} else {
			System.out.println("There isn't a bench at this location.");
		}
	}


	void attack(String word2) {

		if (itemMap.get(word2) == null) {

			if (word2.equals("hornet")) {
				if (currentRoom.equals("Greenpath") && hornet.lives != 0) {

					hornet.lives--;
					player.activeCombat = true;
					if (hornet.lives == 0) {
						System.out.println("You have defeated the mighty hornet! The cloak is yours to take.");
						player.activeCombat = false;
					}

					if (player.activeCombat) {
						double chance = Math.random();

						if (chance <= 0.33) {
							player.healthPoints--;
							System.out.println("You have been struck!");
							if (player.healthPoints == 0) {
								player.alive = false;
								player.activeCombat = false;
							}
						} else {
							System.out.println("You had a near miss with hornet's attack, now's your chance!");
						} 
					}

				} else {
					System.out.println("You can't do that here.");
				}
			}

			if (word2.equals("hollow")) {
				if (currentRoom.equals("Temple Of The Black Egg") && hollowKnight.lives != 0) {

					hollowKnight.lives--;
					player.activeCombat = true;
					if (hollowKnight.lives == 0) {
						ending = true;
					}

					if (player.activeCombat) {
						double chance =  Math.random();

						if (chance <= 0.50) {
							player.healthPoints--;
							System.out.println("You have been struck!");
							if (player.healthPoints == 0) {
								player.alive = false;
								player.activeCombat = false;
							}
						} else {
							System.out.println("You had a near miss with the Hollow Knight's attack, now's your chance!");
						} 
					}

				} else {
					System.out.println("You can't do that here.");
				}
			}
		} else {

			if (itemMap.get(word2).isAttackable == true) {
				itemMap.get(word2).healthPoints--;
			}

			if (word2.equals("rock") && currentRoom.equals("Forgotten Crossroads")) {
				if (itemMap.get(word2).healthPoints == 0) {
					System.out.println("Wow! There was geo incrusted inside the rock! You gain +100 geo. This is a valuable mineral.");
					roomMap.get(currentRoom).description = "The streets of Hallownest which were once filled with traffic. Now they seem abandoned and only the ruins remain."
							+ "To the North, there is a room resembling a large, cracked black egg, looks like something mysterious lies inside."
							+ "Directly west there is a door which is locked." + "Directly south there is a wall."  + "To the east, a narrow and pitch black passage begins.";
					if (word2.equals("rock")) {
						roomMap.get(currentRoom).description = "The streets of Hallownest which were once filled with traffic. Now they seem abandoned and only the ruins remain. To the North, there is a room resembling a large, cracked black egg, looks like something mysterious lies inside."
								+ "Directly west there is a door which is locked. Directly south there is a wall. To the east, a narrow and pitch black passage begins.";
						player.geo = 100;
						inventory.add(player.geo + " geo");
					}
					itemMap.remove(word2);
				}
			} else {
				System.out.println("You can't do that. ");
			}
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

		if (inventory.contains("cloak")) {
			roomMap.get("Fungal Wastes").locked = false;
		}

		if (dreamerSeals == 0) {
			roomMap.get("Temple Of The Black Egg").locked = false;
		}
		if (roomMap.get(newRoom).locked) {
			if (newRoom.equals("Greenpath")) {
				System.out.println("The door is locked. ");
			}
			if (newRoom.equals("Fungal Wastes")) {
				System.out.println("There is a large gap in the way, your character cannot go this way normally. ");
			}
			if (newRoom.equals("City of Tears")) {
				System.out.println("There is a wall that must be climbed. You don't have that ability as of now. ");
			}
			if (newRoom.equals("Resting Grounds")) {
				System.out.println("It is too dark for you to nagivate this passage safely. You can't see anything. ");
			}
			if (newRoom.equals("Deepnest")) {
				System.out.println("This area is pitch black. You can't see anything, so you can't explore it. ");
			}
			if (newRoom.equals("Temple Of The Black Egg")) {
				System.out.println("Entrance is sealed by three mysterious beings through a subconscious force, like three seals. ");
			}
			return;
		}
		currentRoom = newRoom;
		lookAtRoom();

	}

	void pickUpItem(String object) {

		if (roomMap.get(currentRoom).itemList.contains(itemMap.get(object))) {

			if (object.equals("supplies")) {
				System.out.println("You have to be more specific, which supply do you want to take?");
			}
			if (object.equals("cloak")) {
				if (hornet.lives != 0) {
					System.out.println("If you want the cloak, you must defeat Hornet. Do you wish to attack now?");
					return;
				} else {
					roomMap.get(currentRoom).description = "This lively part of the kingdom is filled with blooming flowers and growing vegetation. There is a bench. "
							+ "To the south, there is a path that has a large gap.To the east there is a opened door ";
				}
			}

			if (!inventory.contains(object)) {
				if (itemMap.get(object).isCarryable) {
					inventory.add(object);
					System.out.println("You are now carrying a " + object + ".");
				} else {
					System.out.println("This is not carryable.");
				}				
			} else {
				System.out.println("You already have that.");
			}

		} else {
			System.out.println("You can't take that right now.");
		}	

	}

	void showInventory() {

		System.out.println("Current inventory:");
		System.out.println("-----------------     HP: " + player.healthPoints);
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).toString());
		}
	}

	void printHelp() {

		System.out.println("Here are all of your controls: \n-----------------------------");
		System.out.println("n/north - go north            | s/south - go south");
		System.out.println("e/east - go east              | w/west - go west");
		System.out.println("u - go up                     | d - go down");
		System.out.println("i/inventory - shows inventory | pickup/take - pick up item in current location");
		System.out.println("examine - looks at object with more detail");
		System.out.println("buy - purchase items (only applies to special objects with this property)");
		System.out.println("eat - eat item (only applies to special objects with this property)");
		System.out.println("attack - allows you to attack things (only applies to special objects with this property)");
		System.out.println("look - examines the surrounding area around you");
		System.out.println("take - picks up the chosen item (only applies to certain items)");
		System.out.println("open - opens a door that is closed or locked.");
		System.out.println("sit - sits on a bench, if you die, you'll respawn there. Sort of like a checkpoint. ");
		System.out.println("turn on - powers on an item (only applies to special objects with this property).");
		System.out.println("turn off - powers off an item (only applies to special objects with this property).");
		System.out.println("dream nail - dream nails a higher being, allowing the wielder to cut through the veil between dreams and waking");

	}

	void examineObject(String item) {

		if (roomMap.get(currentRoom).itemList.contains(itemMap.get(item)) || inventory.contains(item)) {		
			System.out.println(itemMap.get(item).getDescr());
		} else {
			System.out.println("You can't examine that right now.");
		}

	}

	void eatItem(String item) {
		if (inventory.contains(item)) {
			if (item.equals("fungi") || item.equals("fungus")) {
				itemMap.get("fungi").isActivated = true;
				inventory.remove(item);
				roomMap.get("City of Tears").locked = false;
				System.out.println("You have consumed a fungus. This gives you the ability to climb walls to go in certain locations. ");
			} else {
				System.out.println("You can't eat that. ");
			}
		} else {
			System.out.println("You don't have that item in your inventory. ");
		}
	}

	void openDoor(String item) {

		if (item.equals("door")) {
			if (!inventory.contains("key")) {
				System.out.println("You don't have the key needed. ");
				return;
			} else {
				roomMap.get("Greenpath").locked = false;
				System.out.println("The door has been unlocked. ");
			}
		} else {
			System.out.println("You can't open that. ");
		}
	}

	void turnOnItem(String item) {
		if (inventory.contains(item)) {
			if (item.equals("flashlight")) {
				itemMap.get(item).isActivated = true;
				System.out.println("Your flashlight is now on. ");
				roomMap.get("Resting Grounds").locked = false;
				roomMap.get("Deepnest").locked = false;
			} else {
				System.out.println("You can't turn that on. ");
			}
		} else {
			System.out.println("You don't have that item in your inventory. ");
		}
	}

	void turnOffItem(String item) {
		if (inventory.contains(item)) {
			if (item.equals("flashlight")) {
				itemMap.get(item).isActivated = false;
				System.out.println("Your flashlight is now off. ");
				roomMap.get("Resting Grounds").locked = true;
				roomMap.get("Deepnest").locked = true;
			} else {
				System.out.println("You can't turn that off. ");
			}
		} else {
			System.out.println("You don't have that item in your inventory. ");
		}
	}

	void dreamNailItem(String item) {

		if (inventory.contains("dreamnail")) {
			if (item.equals("herrah") && currentRoom.equals("Deepnest")) {
				dreamerSeals--;
				System.out.println("Herrah's eternal dream has ended and its seal has been destroyed.");
				roomMap.get(currentRoom).description = "The deepest area of Hallownest. "
						+ "Don't stay for long in this area, or you will end up sharing the fate of many other travellers who thought they were brave enough to stay. "
						+ "The only exit is up.";
			}
			else if (item.equals("monomon") && currentRoom.equals("Resting Grounds")) {
				dreamerSeals--;
				System.out.println("Monomon's eternal dream has ended and its seal has been destroyed.");
				roomMap.get(currentRoom).description = "This serene, sacred area used to be where inhabitants of Hallownest conducted rituals for the dead."
						+ "To the West is a small passageway.";
			}
			else if (item.equals("lurien") && currentRoom.equals("City of Tears")) {
				dreamerSeals--;
				System.out.println("Lurien's eternal dream has ended and its seal has been destroyed.");
				roomMap.get(currentRoom).description = "Welcome to the capital city of Hallownest. Due to being located under the Blue Lake, it is always raining. There is a silver bench. "
						+ "Nearby, the remaining supplies of a past explorer can be found. There is a small exit north that takes you through a secret pathway. West is Fungal Wastes.";
			} else {
				System.out.println("You can't dream nail that right now");
			}
		} else {System.out.println("You don't have a dream nail yet.");}
	}
}

