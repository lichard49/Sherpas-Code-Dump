package com.sherpas.wheresmystuff.model;

import java.util.Date;

public class DBItem
{
	private long ID, posterID, resolverID;
	private String name, description, type, category;
	private int typeID, categoryID;
	private double lat, lon, reward;
	private Date datePosted, dateResolved;
	private boolean isResolved;
	
	public DBItem(long ID, String name, String description, int typeID, String type, int categoryID, String category,
			boolean isResolved, long posterID, Date datePosted)
	{
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.typeID = typeID;
		this.type = type;
		this.categoryID = categoryID;
		this.category = category;
		this.isResolved = isResolved;
		this.posterID = posterID;
		this.datePosted = datePosted;
		this.lat = -181;
		this.lon = -181;
	}
	
	public DBItem(long ID, String name, String description, int typeID, int categoryID,
			boolean isResolved, long posterID, Date datePosted)
	{
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.typeID = typeID;
		this.categoryID = categoryID;
		this.isResolved = isResolved;
		this.posterID = posterID;
		this.datePosted = datePosted;
		this.lat = -181;
		this.lon = -181;
	}
	
	public long getID()
	{
		return ID;
	}
	
	public long getPosterID()
	{
		return posterID;
	}
	
	public long getResolverID()
	{
		return resolverID;
	}
	
	public void setResolverID(long resolverID)
	{
		this.resolverID = resolverID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public int getTypeID()
	{
		return typeID;
	}
	
	public void setTypeID(int typeID)
	{
		this.typeID = typeID;
	}
	
	public int getCategoryID()
	{
		return categoryID;
	}
	
	public void setCategoryID(int categoryID)
	{
		this.categoryID = categoryID;
	}
	
	public double getLatitude()
	{
		return lat;
	}
	
	public double getLongitude()
	{
		return lon;
	}
	
	public void setLocation(double lat, double lon)
	{
		this.lat = lat;
		this.lon = lon;
	}
	
	public double getReward()
	{
		return reward;
	}
	
	public void setReward()
	{
		this.reward = reward;
	}
	
	public Date getDatePosted()
	{
		return datePosted;
	}
	
	public Date getDateResolved()
	{
		return dateResolved;
	}
	
	public void setDateResolved(Date dateResolved)
	{
		this.dateResolved = dateResolved;
	}
	
	public boolean isResolved()
	{
		return isResolved;
	}
	
	public void setIsResolved(boolean isResolved)
	{
		this.isResolved = isResolved;
	}
}
