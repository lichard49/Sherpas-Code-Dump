package com.sherpas.wheresmystuff.support;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.Person;

/**
 * A custom ArrayAdapter that parses Person objects and renders them in a
 * list properly, with names and appropriate buttons
 * 
 * @author Richard
 *
 */
public class PersonAdapter extends ArrayAdapter<Person>
{
	private Context context;
	private int resId;
	private Person[] people;
	
	/**
	 * Constructor that assigns initial values for the adapter to operate
	 * 
	 * @param c context
	 * @param r resource ID
	 * @param p list of people
	 */
	public PersonAdapter(Context c, int r, Person[] p)
	{
		super(c, r, p);
		context = c;
		resId = r;
		people = p;
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
		PersonHolder holder = null;
		
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(resId, parent, false);
			
			holder = new PersonHolder();
			holder.name = (TextView) row.findViewById(R.id.person_name);
			holder.admin = (ImageButton) row.findViewById(R.id.make_admin_button);
			holder.delete = (ImageButton) row.findViewById(R.id.delete_button);
			holder.lock = (ImageButton) row.findViewById(R.id.lock_button);
			
			row.setTag(holder);
		}
		else
		{
			holder = (PersonHolder) row.getTag();
		}
		
		Person p = people[pos];
		holder.name.setText(p.getEmail());
		if(p.isAdmin())
		{
			holder.admin.setImageResource(R.drawable.makeadmin);
		}
		else
		{
			holder.admin.setImageResource(R.drawable.nonadmin);
		}
		
		if(p.isLocked())
		{
			holder.lock.setImageResource(R.drawable.unlock);
		}
		else
		{
			holder.lock.setImageResource(R.drawable.lock);
		}
		holder.delete.setImageResource(R.drawable.delete);
		
		return row;
	}
	
	public int getCount()
	{
		return people.length;
	}
	
	/**
	 * Holder for the widgets of a given row
	 * 
	 * @author Richard
	 *
	 */
	private class PersonHolder
	{
		TextView name;
		ImageButton admin;
		ImageButton lock;
		ImageButton delete;
	}
}