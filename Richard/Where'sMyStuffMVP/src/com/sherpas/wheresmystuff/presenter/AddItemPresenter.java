package com.sherpas.wheresmystuff.presenter;

import java.sql.Date;
import java.util.Map;

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
	
	public void submitItem(String t, String n, String d, String c, 
			String l, String r, Date date, Person owner)
	{
		/*
		Item submittedItem = new Item(owner);
		submittedItem.addAttribute(Item.Attribute.TYPE, t);
		submittedItem.addAttribute(Item.Attribute.NAME, n);
		submittedItem.addAttribute(Item.Attribute.DESCRIPTION, d);
		submittedItem.addAttribute(Item.Attribute.CATEGORY, c);
		submittedItem.addAttribute(Item.Attribute.LOCATION, l);
		submittedItem.addAttribute(Item.Attribute.REWARD, r);
		submittedItem.addAttribute(Item.Attribute.DATE, date);
		model.addItem(submittedItem);
		*/
		Map<String, Integer> typeMap = model.getTypeTable();
		Map<String, Integer> categoryMap = model.getCategoryTable();
		System.out.println("Type: " + typeMap + "... category: " + categoryMap);
		System.out.println(t + ", " + n + ", " + d + ", " + c + ", " + l + ", " + r + ", " + date + ", " + owner);
		int type = typeMap.get(c);
		int category = categoryMap.get(t);
		model.addItem(n, d, type, category, 
				false, date, owner.getID());
		view.toHomeActivity();
	}
}