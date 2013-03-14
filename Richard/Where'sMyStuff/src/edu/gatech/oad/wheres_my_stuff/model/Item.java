package edu.gatech.oad.wheres_my_stuff.model;

import android.graphics.Bitmap;


/**
 * Represents a single Person in the model
 * Information Holder 
 * 
 * @author Prem
 *
 */
public class Item 
{
	
	/** the item's itemName */
	private String name;
	
	/*private String uid;*/
	
	/** the item's associated person*/
	private String personUserName;
	
	/** the item's description*/
	private String description;
	
	/** the item's itemImage */
	private Bitmap itemImage;
	
	/**
	 * Makes a new item
	 * @param n the name
	 * @param u the id
	 * @param e the email
	 */
	public Item(String name, String personUserName, String description, Bitmap itemImage) 
	{
		this.name = name;
		this.personUserName = personUserName;
		this.description = description;
		this.itemImage = itemImage;
	}
	
	/**
	 * 
	 * @return the item's name
	 */
	public String getName() { return name; }
	/**
	 * 
	 * @return the item's relative person
	 */
	public String getPerson() { return personUserName; }
	/**
	 * 
	 * @return the persons id
	 */
	/*public String getUid() { return uid; }
	*//**
	 * 
	 * @return the item's description
	 */
	public String getDescription() { return description; }
	
	/**
	 *  
	 * @return the item's Image 
	 */
	public Bitmap getImage() { return itemImage; }

	
}
