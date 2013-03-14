package edu.gatech.oad.wheres_my_stuff.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A temporary class before MySQL and SQLite implementation is put in place.
 * Keeps track of data about users and items.
 * 
 * @author Richard
 *
 */

public class Database2 
{
	private static Map<String, Person> peopleMap;
	static 
	{
	    peopleMap = new HashMap<String, Person>();
	     
	    Person temp=new Person("a","t","t.com");
        peopleMap.put(temp.getEmail(),temp);
        
        Person admin = new Person("admin", "admin", "admin");
        peopleMap.put(admin.getEmail(),admin);
	}
	public static String[] getListNames()
	{
		Collection<Person> c = Database2.peopleMap.values();
		Person[] people = new Person[c.size()];
		c.toArray(people);
		String[] names = new String[c.size()];
		for(int x = 0; x < c.size(); x++)
		{
			names[x] = people[x].getFirstName();
		}
		return names;
	}
	public static Person getPerson(String email)
	{
		return peopleMap.get(email);
	}
	public static void addPerson(Person p)
	{
		peopleMap.put(p.getEmail(), p);
	}
	public static void removePerson(String email)
	{
		peopleMap.remove(email);
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