package edu.gatech.oad.wheres_my_stuff.model;

import java.util.HashMap;
import java.util.Map;



public class Database {

	public static  Map<String, Person> map;
	static {
	    map = new HashMap<String, Person>();
	     
	       Person temp=new Person("a","t","t.com", "t");
	        map.put(temp.getEmail(),temp);
	}
	public static  Map<String, Item> itemMap;
	static {
	    itemMap = new HashMap<String, Item>();
	     
	       Item temp=new Item("sandwich","t.com","favorite meal",null);
	        itemMap.put(temp.getName(),temp);
	}
	public static Person loggedIn;
}