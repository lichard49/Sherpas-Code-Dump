package edu.gatech.oad.wheres_my_stuff.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;

public class AdminActivity extends Activity 
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_list);
		
		ListView list = (ListView) findViewById(R.id.list_of_users);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.admin_list_item_layout, 
				R.id.person_name, Database.getListNames());
		list.setAdapter(adapter);
	}
}
