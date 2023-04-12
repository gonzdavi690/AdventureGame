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
	
	Item(String name){
		this.name = name;
	}
}
