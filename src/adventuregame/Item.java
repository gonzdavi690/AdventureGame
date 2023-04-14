package adventuregame;

public class Item {
	
	String name;
	String descr;
	int damage;
	int healthPoints;
	boolean isAttackable = false;
	boolean isCarryable = true;
	boolean isActivated = false;
	
	String getDescr() {return descr;}
	String getName() {return name;}	
	
	Item(){}
	
	Item (String name) {
		this.name = name;
	}
	Item(String name, String description){
		this.name = name;
		this.descr = description;
	}
}
