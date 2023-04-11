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
		r.setExits("Shop", "", "", "", "", "Forgotten Crossroads");
		roomList.put("Dirtmouth", r);
		
		r = new Room("Sly's shop", "For sale: Simple Key - 100 geo /  "
				+ "The exit is back south");
		r.setExits("", "Dirtmouth", "", "", "", "");
		roomList.put("Sly's shop", r);
		
		r = new Room("Forgotten Crossroads", "The streets of Hallownest which were once filled with traffic. Now they seem abandoned and only the ruins remain."
				+ "Directly north there is a door which is locked" + "Directly south there is a wall" + "To the west, there is a large crate guarded by a  "); 
		r.setExits("frontYard", "bedroom1", "livingroom", "kitchen", "", "");
		roomList.put("Forgotten Crossroads", r);
	}
}
  