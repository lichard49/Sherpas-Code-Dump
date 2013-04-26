package com.sherpas.wheresmystuff.support;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DBItem;

/**
 * Generates the adapter for use with the list
 */
public class ItemAdapter extends ArrayAdapter<DBItem>
{
	private Context context;
	private int resId;
	private DBItem[] items;
	
	/**
	 * Constructor 
	 * 
	 * @param c
	 * @param res
	 * @param data
	 */
	public ItemAdapter(Context c, int res, DBItem[] data)
	{
		super(c, res);
		context = c;
		resId = res;
		items = data;
	}

	/**
	 * Gets any given view of the list
	 * 
	 * @param pos position of the view to get
	 * @param convert the view to convert
	 * @param parent the parent of the view to get 
	 */
	public View getView(int pos, View convert, ViewGroup parent)
	{
		View row = convert;
		ItemHolder holder = null;
		
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(resId, parent, false);
			
			holder = new ItemHolder();
			holder.name = (TextView) row.findViewById(R.id.item_name);
			
			row.setTag(holder);
		}
		else
		{
			holder = (ItemHolder) row.getTag();
		}
		
		DBItem i = items[pos];
		//holder.name.setText(i.getName() + "   ... " + i.getCategory());
		if(i.getID() == -1)
			holder.name.setText(DBItem.EMPTY_ITEM);
		else
			holder.name.setText(i.getName());// + "..." + i.getTypeID() + "..." + i.getCategoryID());
		
		return row;
	}
	
	public int getCount()
	{
		return items.length;
	}
	
	/**
	 * Holder for the widgets of a given row
	 * 
	 * @author Richard
	 *
	 */
	private class ItemHolder
	{
		TextView name;
	}
}
