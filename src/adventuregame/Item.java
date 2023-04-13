package adventuregame;

public class Item {
	
	int damage;
	String name;
	String descr;
	boolean isCarryable = true;
	int healthPoints;
	boolean isActivated;
	boolean isReadable;
	
	Item(){}
	
	
	Item (String name) {
		this.name = name;
	}
	Item(String name, String description){
		this.name = name;
		this.descr = description;
	}
}
