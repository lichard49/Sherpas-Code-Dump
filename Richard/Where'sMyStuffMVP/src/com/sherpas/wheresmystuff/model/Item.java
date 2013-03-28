package com.sherpas.wheresmystuff.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

/**
 * Information holder about an item's details
 * 
 * @author Richard
 *
 */
public class Item implements Serializable
{
	private static final long serialVersionUID = 5291141349742633126L;

	/**
	 * The attributes of an item
	 */
	public enum Attribute
	{
		TYPE, NAME, DESCRIPTION, CATEGORY, LOCATION, REWARD, DATE;
	}
	
	private Map<Attribute, String> attributes;
	private Person owner;
	private Bitmap image;
	
	/**
	 * Constructor that gives the item an owner
	 * 
	 * @param owner Person that owns the item
	 */
	public Item(Person owner)
	{
		this.owner = owner;
		attributes = new HashMap<Attribute, String>();
	}
	
	/**
	 * Adds an attribute to this item
	 * 
	 * @param attribute the attribute to be added
	 * @param detail the information about the attribute
	 */
	public void addAttribute(Attribute attribute, String detail)
	{
		attributes.put(attribute, detail);
	}
	
	/**
	 * Gets a specific attribute about this item
	 * 
	 * @param attribute the attribute to get
	 * @return 
	 */
	public String getAttribute(Attribute attribute)
	{
		return attributes.get(attribute);
	}
	
	/**
	 * Gets the Person object that owns this item
	 * 
	 * @return Person that owns the item
	 */
	public Person getOwner()
	{
		return owner;
	}
	
	public Bitmap getImage()
	{
		return image;
	}
	
	public void setImage(Bitmap i)
	{
		image = i;
	}
	
	public String toString()
	{
		String result = "";
		for(String s: attributes.values())
		{
			result += s + " ";
		}
		return result;
	}
}
