package com.sherpas.wheresmystuff.command;

import java.util.Date;

import android.location.Location;

import com.sherpas.wheresmystuff.model.DBItem;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.support.GpsManager;

public class AddItemCommand implements ICommand
{
	private long posterID;
	private String name, description;
	private int typeID, categoryID;
	private Date date;
	
	private DBItem addedItem;
	private IDatabaseModel db;
	
	public AddItemCommand(String name, String description, int typeID,
			int categoryID, long posterID,
			Date date, IDatabaseModel d)
	{
		this.name = name;
		this.description = description;
		this.typeID = typeID;
		this.categoryID = categoryID;
		this.posterID = posterID;
		this.date = date;
		db = d;
	}
	
	@Override
	public boolean execute()
	{
		Location l = null;//GpsManager.getInstance().getCurrentLocation();
		System.out.println("Location " + l);
		if(l == null)
		{
			addedItem = db.addItem(name, description, typeID, categoryID, 
					false, 33.777121, -84.396053, posterID, date);
		}
		else
		{
			addedItem = db.addItem(name, description, typeID, categoryID, 
					false, l.getLatitude(), l.getLongitude(), posterID, date);
		}
		return addedItem == null;
	}

	@Override
	public boolean undo()
	{
		//db.
		return false;
	}

	public DBItem getAddedItem()
	{
		return addedItem;
	}
}
