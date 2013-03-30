package com.sherpas.wheresmystuff.presenter;

import java.sql.Date;
import java.util.Map;

import android.graphics.Bitmap;

import com.sherpas.wheresmystuff.model.DBItem;
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
	public void submitItem(String t, String n, String d, String c, 
			String l, String r, Date date, Bitmap i, Person owner)
	{
		Map<String, Integer> typeMap = model.getTypeTable();
		Map<String, Integer> categoryMap = model.getCategoryTable();
		System.out.println("Type: " + typeMap + "... category: " + categoryMap);
		System.out.println(t + ", " + n + ", " + d + ", " + c + ", " + l + ", " + r + ", " + date + ", " + owner);
		int type = typeMap.get(t);
		int category = categoryMap.get(c);
		DBItem addedItem = model.addItem(n, d, type, category, 
				false, owner.getID(), date);
		
		if(addedItem != null)
		{
			model.createImage(addedItem.getID(), 0, i);
			System.out.println("image added");
		}
		
		view.toHomeActivity();
	}
	
	public void startCamera()
	{
		view.toTakePicture();
	}
}