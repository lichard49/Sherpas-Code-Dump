package edu.gatech.oad.wheres_my_stuff.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MyItem implements Serializable
{
	public enum Attribute
	{
		TYPE, NAME, DESCRIPTION, CATEGORY, LOCATION, REWARD, DATE;
	}
	
	private Map<Attribute, String> attributes;
	private Person owner;
	private Bitmap image;
	
	public MyItem(Person owner)
	{
		this.owner = owner;
		attributes = new HashMap<Attribute, String>();
	}
	
	public void addAttribute(Attribute attribute, String detail)
	{
		attributes.put(attribute, detail);
	}
	
	public Person getOwner()
	{
		return owner;
	}
	
	public String getAttribute(Attribute attribute)
	{
		return attributes.get(attribute);
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
