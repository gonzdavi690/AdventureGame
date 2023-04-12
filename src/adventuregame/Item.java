package adventuregame;

public class Item {
	
	int damage;
	String name;
	String descr;
	boolean isCarryable = true;
	int healthPoints;
	boolean isActivated;
	
	Item(){}
	
	Item(String name){
		this.name = name;
	}
}
