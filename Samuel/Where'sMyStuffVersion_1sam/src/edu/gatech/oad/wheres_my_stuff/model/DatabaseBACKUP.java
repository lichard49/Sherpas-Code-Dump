package edu.gatech.oad.wheres_my_stuff.model;

import java.util.HashMap;
import java.util.Map;



public class DatabaseBACKUP{

	public static  Map<String, Person> map;
	static {
	    map = new HashMap<String, Person>();
	     
	       Person temp=new Person("a","t","t.com", "t");
	        map.put(temp.getEmail(),temp);
	}
	public static Person loggedIn;
}