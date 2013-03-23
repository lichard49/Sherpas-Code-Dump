package edu.gatech.oad.wheres_my_stuff.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;
import edu.gatech.oad.wheres_my_stuff.model.Person;

/**
 * The administrator's page that consists of a list all users with the ability
 * to unlock and delete users
 * 
 * @author Richard
 *
 */
public class AdminActivity extends Activity
{
	/**
	 * Called at the start of the Activity and handles setting up the list
	 * 
	 * @param savedInstanceState the state of this Activity at the end of its
	 * last session
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_list);
		
		ListView list = (ListView) findViewById(R.id.list_of_users);
		PersonAdapter adapter = new PersonAdapter(this,
				R.layout.admin_list_item_layout,
				personListToArray(Database.getInstance().getAllUsers()));
				
		list.setAdapter(adapter);
		
	}
	
	/**
	 * Extra method that converts of Person objects to a list of all of the
	 * people's names
	 * 
	 * @param list list of Person objects
	 * @return list of the Person's names
	 */
	@SuppressWarnings("unused")
	private String[] personListToName(List<Person> list)
	{
		String[] names = new String[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			names[i] = list.get(i).getEmail();
		}
		return names;
	}
	
	/**
	 * Converts a List of Person objects into an array for the ArrayAdapter
	 * 
	 * @param list list of Person objects
	 * @return array of Person objects
	 */
	private Person[] personListToArray(List<Person> list)
	{
		Person[] people = new Person[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			people[i] = list.get(i);
		}
		return people;
	}

	/**
	 * Called when a lock button is pressed, the appropriate user is locked
	 * 
	 * @param v View that was pressed
	 */
	public void lockButtonHandler(View v) 
	{
		String pressedEmail = getSelectedEmail(v);
		Database.getInstance().setLocked(pressedEmail,
				!Database.getInstance().emailLocked(pressedEmail));
		System.out.println(pressedEmail + "'s lock state: " + 
				Database.getInstance().emailLocked(pressedEmail));
		refresh();
	}
	
	public void makeAdminButtonHandler(View v)
	{
		String pressedEmail = getSelectedEmail(v);
		Database.getInstance().setAdmin(pressedEmail, true);
	}

	/**
	 * Called when a delete button is pressed, the appropriate user is deleted
	 * 
	 * @param v View that was pressed
	 */
	public void deleteButtonHandler(View v)
	{
		String pressedEmail = getSelectedEmail(v);
		Database.getInstance().deleteUser(pressedEmail);
		System.out.println(pressedEmail + " was just deleted!");
		refresh();
	}
	
	/**
	 * Refreshes the Activity by killing (finishing) it and starting it again 
	 */
	private void refresh()
	{
		Intent thisIntent = getIntent();
		finish();
		startActivity(thisIntent);
	}
	
	/**
	 * Takes the child of a layout and looks at its parent layout in order to
	 * find the text containing its owner's name
	 * 
	 * @param child child layout to find the owner of
	 * @return owner's name
	 */
	public String getSelectedEmail(View child)
	{
		RelativeLayout root = (RelativeLayout) child.getParent();
		TextView text = null;
		for(int i = 0; i < root.getChildCount(); i++)
		{
			if(root.getChildAt(i) instanceof TextView)
			{
				text = (TextView) root.getChildAt(i);
				break;
			}
		}
		String pressedEmail = text.getText().toString();
		return pressedEmail;
	}
	
	/**
	 * A custom ArrayAdapter that parses Person objects and renders them in a
	 * list properly, with names and appropriate buttons
	 * 
	 * @author Richard
	 *
	 */
	private class PersonAdapter extends ArrayAdapter<Person>
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
				holder.delete = (ImageButton) row.findViewById(R.id.delete_button);
				holder.lock = (ImageButton) row.findViewById(R.id.lock_button);
				
				row.setTag(holder);
			}
			else
			{
				holder = (PersonHolder) row.getTag();
			}
			
			Person p = people[pos];
			System.out.println("Holder " + holder + "  name " + holder.name + "  p " + p);
			holder.name.setText(p.getEmail());
			// TODO: change this to use the person's field
			if(Database.getInstance().emailLocked(p.getEmail()))
			{
				holder.lock.setImageResource(R.drawable.unlock);
			}
			else
			{
				holder.lock.setImageResource(R.drawable.unlock_pressed);
			}
			holder.delete.setImageResource(R.drawable.delete);
			
			return row;
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
			ImageButton lock;
			ImageButton delete;
		}
	}
}
