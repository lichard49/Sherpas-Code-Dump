package com.sherpas.wheresmystuff.presenter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.sherpas.wheresmystuff.model.DBImage;
import com.sherpas.wheresmystuff.model.DBItem;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.view.IViewItemView;

public class ViewItemPresenter
{
	private final IViewItemView view;
	private final IDatabaseModel model;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public ViewItemPresenter(IViewItemView v, IDatabaseModel m)
	{
		view = v;
		model = m;
	}
	
	public DBItem getItem(String name)
	{
		for(DBItem i: model.getAllItems())
		{
			if(i.getName().equals(name))
			{
				return i;
			}
		}
		return null;
	}
	
	public ArrayList<DBImage> getImage(DBItem item)
	{
		return model.getImages(item.getID());
	}
	
	public DBItem[] getSameNameItem(DBItem s)
	{
		ArrayList<DBItem> result = new ArrayList<DBItem>();
		for(DBItem i: model.getAllItems())
		{
			if((i.getName().contains(s.getName()) ||
					s.getName().contains(i.getName())) 
					&& i.getID() != s.getID())
			{
				result.add(i);
			}
		}
		DBItem[] result1 = new DBItem[result.size()];
		for(int i = 0; i < result1.length; i++)
		{
			result1[i] = result.get(i);
		}
		return result1;
	}
	
	public String getCategory(int id)
	{
		Map<String, Integer> map = model.getCategoryTable();
		for(Entry<String, Integer> e: map.entrySet())
		{
			if(e.getValue() == id) return e.getKey();
		}
		return "";
	}
	
	public String getType(int id)
	{
		Map<String, Integer> map = model.getTypeTable();
		for(Entry<String, Integer> e: map.entrySet())
		{
			if(e.getValue() == id) return e.getKey();
		}
		return "";
	}
}
