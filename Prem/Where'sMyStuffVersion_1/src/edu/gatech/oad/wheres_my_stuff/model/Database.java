package edu.gatech.oad.wheres_my_stuff.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A temporary class before MySQL and SQLite implementation is put in place.
 * Keeps track of data about users and items.
 * 
 * @author Richard
 *
 */

public class Database 
{
	public static  Map<String, Person> map;
	static 
	{
	    map = new HashMap<String, Person>();
	     
	    Person temp=new Person("a","t","t.com", "t");
        map.put(temp.getEmail(),temp);
	}
	
	private static Map<String, MyItem> itemMap;
	static 
	{
	    itemMap = new HashMap<String, MyItem>();
	     
	    //MyItem temp=new MyItem("sandwich","t.com","favorite meal",null);
	    //itemMap.put(temp.getName(),temp);
	}
	
	public static void addItem(MyItem i)
	{
		//currentItems.add(i);
		itemMap.put(i.getAttribute(MyItem.Attribute.NAME), i);
	}
	
	public static MyItem getItem(String s)
	{
		return itemMap.get(s);
	}
	
	//private static ArrayList<MyItem> currentItems = new ArrayList<MyItem>();
	public static String[] getItemList()
	{
		String[] result = new String[itemMap.size()];
		itemMap.keySet().toArray(result);
		return result;
	}
}