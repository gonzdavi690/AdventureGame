package adventuregame;

import java.util.ArrayList;
import java.util.HashMap;

class Room {

	private String title;
	private String description;	
	private String N,S,E,W,U,D;

	// items in this room FIXME (should this be private?)
	ArrayList<Item> itemList = new ArrayList<Item>();

	Room(String t, String d) {
		title = t;
		description = d;
	}

	//FIXME should this be private?
	private void setExits(String N, String S, String W, String E, String U, String D) {
		this.N = N;
		this.S = S;
		this.W = W;
		this.E = E;
		this.U = U;
		this.D = D;
	}

	String getExit(char c) {
		switch (c) {
		case 'n': return this.N;
		case 's': return this.S;
		case 'w': return this.W;
		case 'e': return this.E;
		case 'u': return this.U;
		case 'd': return this.D;
		default: return null;
		}
	}

	String getTitle() {return title;}
	String getDesc() {return description;}

	//ONLY done at the beginning of the game
	static void setupRooms(HashMap<String,Room> roomList) {
		Room r = new Room("Dirtmouth", "A once lively village that has since fallen silent. A sickly air fills the place." + 
				" There is a bench. To the north there is a bulding with a locked door and a sign that says: shop coming soon." 
				+ "There is a large well to the with a rope set up for travellers who venture down into the kindom, none of which have returned.");
		//          N S W E U D
		r.setExits("Sly's shop", "", "", "", "", "Forgotten Crossroads");
		roomList.put("Dirtmouth", r);

		r = new Room("Sly's shop", "For sale: Simple Key - 100 geo /  "
				+ "The exit is back south");
		r.setExits("", "Dirtmouth", "", "", "", "");
		roomList.put("Sly's shop", r);

		r = new Room("Forgotten Crossroads", "The streets of Hallownest which were once filled with traffic. Now they seem abandoned and only the ruins remain."
				+ "Right beside you, there is a large shiny rock. It looks interesting  "
				+ "Directly west there is a door which is locked" + "Directly south there is a wall" + "To the east, a narrow and pìtch black passage begins."); 
		r.setExits("", "", "Greenpath", "Resting Grounds", "Dirtmouth", "");
		roomList.put("Forgotten Crossroads", r);

		r = new Room("Greenpath", "These lively part of the kingdom is filled with blooming flowers and growing vegetation. There is a bench. Right in the middle, there is a glowing cloak. It is guarded by Hornet, Hallownest's protector."
				+ "She will not give it up without a good fight." + "To the south, there is a path that has a large gap." + "To the east there is a opened door to the Forgotten Crossroads"); 
		r.setExits("", "Fungal Wastes", "", "Forgotten Crossroads", "", "");
		roomList.put("Greenpath", r);

		r = new Room("Fungal Wastes", "Covered with fog and an acrid odour, the Fungal Wastes are home to a species of glowing fungi that inhabit every surface." + "To the North is Greenpath" 
				+ "Going down, there is a steep staircase guarded by half-opened gate with a sign that says danger." + "A short wall is to the East, on top of it an entrance to another area can be seen."); 
		r.setExits("Greenpath", "", "", "City of Tears", "", "Deepnest");
		roomList.put("Fungal Wastes", r);

		r = new Room("City of Tears", "Welcome to the capital city of Hallownest. Due to being located under the Blue Lake, it is always raining. There is a silver bench. Among all this luxury there is a statue of Lurien the Watcher. "
				+ "Nearby, the remaining supplies of a past explorer can be found." + "There is a small exit north that takes you through a secret pathway." + "West is Fungal Wastes."); 
		r.setExits("Forgotten Crossroads", "", "Fungal Wastes", "", "", "");
		roomList.put("City of Tears", r);

		r = new Room("Resting Grounds", "This serene, sacred area used to be where inhabitants of Hallownest conducted rituals for the dead. Laying on the ground there is a pink dream nail. Right beside it, the statue of Monomon the Teacher stands, glowing pink." 
				+ "To the West is a small passageway. " ); 
		r.setExits("", "", "Forgotten Crossroads", "", "", "");
		roomList.put("Resting Grounds", r);

		r = new Room("Deepnest", "The deepest area of Hallownest. In this enormous, spider infested, dark sprawl of narrow passages, the statue of Herrah the Beast can be found. "
				+ "Don't stay for long in this area, or you will end up sharing the fate of many other travellers who thought they were brave enough to stay. " 
				+ "The only exit is up. " ); 
		r.setExits("", "", "", "", "Fungal Wastes", "");
		roomList.put("Greenpath", r);
	}
}
