package com.sherpas.wheresmystuff.model;

import android.graphics.Bitmap;

/**
 * Information holder for images
 * 
 * @author Richard
 *
 */
public class DBImage 
{
	private Bitmap image;
	private long ID;
	private long itemID;
	private int ordinal;
	
	/**
	 * Constructor 
	 * @param ID
	 * @param itemID
	 * @param ordinal
	 * @param image
	 */
	public DBImage(long ID, long itemID, int ordinal, Bitmap image)
	{
		this.ID = ID;
		this.itemID = itemID;
		this.ordinal = ordinal;
		this.image = image;
	}
	
	/**
	 * Gets the image component of the class
	 * @return
	 */
	public Bitmap getImage()
	{
		return image;
	}
}
