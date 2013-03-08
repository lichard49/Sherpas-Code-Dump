package edu.gatech.oad.wheres_my_stuff.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Person;

public class LoginSuccessActivity extends Activity 
{
	private Intent toAddItemActivity;
	private Intent toSearchActivity;
	private Intent toAdminActivity;
	
	private Button adminPanel;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    
        toAddItemActivity = new Intent(getApplicationContext(), AddItemActivity.class);
        toSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
        toAdminActivity = new Intent(getApplicationContext(), AdminActivity.class);
        
        final TextView greeting = (TextView) findViewById(R.id.greeting);
        String name = Person.getLoggedInUser(getApplicationContext()).getFirstName(); //Database.loggedIn.getFirstName();
        greeting.setText("Welcome, " + name + "!");
        
        adminPanel = (Button) findViewById(R.id.admin_panel);
        if(Person.getLoggedInUser(this).isAdmin()) adminPanel.setVisibility(Button.VISIBLE);
        adminPanel.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		startActivity(toAdminActivity);
        	}
        });
        
        ImageButton addItem = (ImageButton) findViewById(R.id.add_item_button);
		addItem.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				startActivity(toAddItemActivity);
			}
		});
		ImageButton searchItem = (ImageButton) findViewById(R.id.search_button);
		searchItem.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				startActivity(toSearchActivity);
			}
		});
	}
}