package com.sherpas.wheresmystuff.presenter;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DBItem;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.support.ItemAdapter;
import com.sherpas.wheresmystuff.view.ISearchView;

public class SearchPresenter
{
	private final ISearchView view;
	private final IDatabaseModel model;
	private Bundle filters;
	private DBItem[] items;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public SearchPresenter(ISearchView v, IDatabaseModel m, Bundle f)
	{
		view = v;
		model = m;
		filters = f;
	}
	
	public ItemAdapter populateItemAdapter(Context c)
	{
		int categoryId = -1;
		System.out.println("Filters: " + filters);
		if(filters.containsKey("category"))
		{
			categoryId = model.getCategoryTable().get(filters.getString("category"));
		}
		
		int typeId = -1;
		if(filters.containsKey("type"))
		{
			typeId = model.getTypeTable().get(filters.getString("type"));
		}
		
		Date date = null;
		if(filters.containsKey("date"))
		{
			date = (Date)filters.getSerializable("date");
		}
		
		String name = "";
		if(filters.containsKey("name"))
		{
			name = (String) filters.getString("name");
		}
		
		//System.out.println("Filters right before render: " + filters);
		List<DBItem> list = model.filterItems(typeId, categoryId, date,
				name, null, null, null);
		
		if(list != null)
		{
			items = new DBItem[list.size()];
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) != null)
				{
					System.out.println(list.get(i).getName());
					items[i] = list.get(i);
				}
			}
		}
		else
		{
			items = new DBItem[1];
			items[0] = new DBItem();
		}
		
		return new ItemAdapter(c, R.layout.search_item_layout, items);
	}
	
	public void onItemClick(int pos)
	{
		System.out.println("pos: " + pos + " item: " + items[pos].getName());
		String itemName = items[pos].getName();
		if(!DBItem.EMPTY_ITEM.equals(itemName))
		{
			System.out.println("Not empty!");
			view.toViewItem(itemName);
		}
		else
		{
			System.out.println("emptyyyy");
		}
	}
	
	public void search(String s)
	{
		filters.putString("name", s);
		view.restart();
	}
}
