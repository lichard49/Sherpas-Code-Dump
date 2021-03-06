package com.sherpas.wheresmystuff.model;

import android.graphics.Bitmap;

public class DBImage 
{
	private Bitmap image;
	private long ID;
	private long itemID;
	private int ordinal;
	
	public DBImage(long ID, long itemID, int ordinal, Bitmap image)
	{
		this.ID = ID;
		this.itemID = itemID;
		this.ordinal = ordinal;
		this.image = image;
	}
	
	public Bitmap getImage()
	{
		return image;
	}
}
