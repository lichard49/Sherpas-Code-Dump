package edu.gatech.oad.wheres_my_stuff.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;

/**
 * Populates the list of items.
 * 
 * @author Richard
 *
 */
public class SearchActivity extends Activity 
{
	private Intent toViewItemActivity;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		toViewItemActivity = new Intent(getApplicationContext(), ViewItemActivity.class);
		
		ListView list = (ListView) findViewById(R.id.search_list_items);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, Database.getItemList());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) 
			{
				toViewItemActivity.putExtra("itemName", 
						Database.getItemList()[pos]);
				startActivity(toViewItemActivity);		
			}
		});
	}
}
