package com.sherpas.wheresmystuff.presenter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
		List<DBItem> list = null;
		String bigF = filters.getString("big");
		if(bigF == null)
		{
			list = model.getAllItems();
		}
		else if(bigF.equals("category"))
		{
			System.out.println("Table: " + model.getTypeTable());
			System.out.println("Filter: " + filters.getString("type"));
			list = model.getItemsByTypeID(model.getTypeTable().get(filters.getString("category")));
		}
		else if(bigF.equals("type"))
		{
			System.out.println("filters: " + filters);
			String categoryVal = filters.getString("type");
			System.out.println("category: " + categoryVal);
			Map<String, Integer> table = model.getCategoryTable();
			System.out.println("table: " + table);
			list = model.getItemsByCategoryID(table.get(categoryVal));
		}
		else if(bigF.equals("date"))
		{
			System.out.println("date: " + (Date)filters.getSerializable("date"));
			list = model.getItemsPostedAfterDate((Date)filters.getSerializable("date"));
		}
		
		DBItem[] items;
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
			items = new DBItem[0];
		}
		
		return new ItemAdapter(c, R.layout.search_item_layout, items);
	}
}
