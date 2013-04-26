package com.sherpas.wheresmystuff.presenter;

import java.sql.Date;
import java.util.Map;

import android.graphics.Bitmap;

import com.sherpas.wheresmystuff.command.AddItemCommand;
import com.sherpas.wheresmystuff.command.CommandProcessor;
import com.sherpas.wheresmystuff.model.DBItem;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.view.IAddItemView;

public class AddItemPresenter
{
	private final IAddItemView view;
	private final IDatabaseModel model;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public AddItemPresenter(IAddItemView v, IDatabaseModel m)
	{
		view = v;
		model = m;
	}
	
	/**
	 * Submitting item to the database
	 * 
	 * @param t type
	 * @param n name
	 * @param d date
	 * @param c category
	 * @param l location
	 * @param r resolved
	 * @param date date
	 * @param i image
	 * @param owner owner
	 */
	public DBItem submitItem(String t, String n, String d, String c, 
			String l, String r, Date date, Bitmap i, Person owner)
	{
		/* Save this */
		//GpsManager.getInstance().getCurrentLocation();
		
		Map<String, Integer> typeMap = model.getTypeTable();
		Map<String, Integer> categoryMap = model.getCategoryTable();
		//System.out.println("Type: " + typeMap + "... category: " + categoryMap);
		//System.out.println(t + ", " + n + ", " + d + ", " + c + ", " + l + ", " + r + ", " + date + ", " + owner);
		int type = 0;
		if(typeMap != null) type = typeMap.get(t);
		int category = 0; 
		if(categoryMap != null) category = categoryMap.get(c);
		
		AddItemCommand cmd = new AddItemCommand(n, d, type, category, 
				owner.getID(), date, DatabaseModel.getInstance());
		CommandProcessor.getInstance().execute(cmd);
		DBItem addedItem = cmd.getAddedItem();
		
//		DBItem addedItem = model.addItem(n, d, type, category, 
//				false, owner.getID(), date);
		
		if(addedItem != null)
		{
			if(i != null)
			{
				model.createImage(addedItem.getID(), 0, i);
				System.out.println("image added");
			}
			addedItem.setCategory(c);
			addedItem.setType(t);
			System.out.println("Newly added item is: " + addedItem + "!");
		}
		
		view.toHomeActivity();
		
		return addedItem;
	}
	
	/**
	 * Starts the Camera intent
	 */
	public void startCamera()
	{
		view.toTakePicture();
	}
}