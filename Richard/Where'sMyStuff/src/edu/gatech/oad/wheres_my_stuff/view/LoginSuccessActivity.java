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

/**
 * The home screen that gives the user navigation options to add items, search
 * items, view favorites, and check mail.  Administrators will also have an
 * extra button allowing access to the administrator panel.
 * 
 * @author Richard
 *
 */
public class LoginSuccessActivity extends Activity 
{
	private Intent toAddItemActivity;
	private Intent toSearchActivity;
	private Intent toAdminActivity;
	
	private Button adminPanel;
	
	/**
	 * Called when the activity is first created, gives functionality to each
	 * of the buttons
	 * 
	 * @param savedInstanceState the state of the Activity at the end of the
	 * last session
	 */
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    
        // sets up activity redirects
        toAddItemActivity = new Intent(getApplicationContext(),
        		AddItemActivity.class);
        toSearchActivity = new Intent(getApplicationContext(),
        		SearchActivity.class);
        toAdminActivity = new Intent(getApplicationContext(),
        		AdminActivity.class);
        
        // general greeting to users
        final TextView greeting = (TextView) findViewById(R.id.greeting);
        String name = Person.getLoggedInUser(getApplicationContext()).
        		getFirstName();
        greeting.setText("Welcome, " + name + "!");
        
        // access to admin panel
        adminPanel = (Button) findViewById(R.id.admin_panel);
        if(Person.getLoggedInUser(this).isAdmin()) 
        	adminPanel.setVisibility(Button.VISIBLE);
        adminPanel.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		startActivity(toAdminActivity);
        	}
        });
        
        // add item button
        ImageButton addItem = (ImageButton) findViewById(R.id.add_item_button);
		addItem.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				startActivity(toAddItemActivity);
			}
		});
		
		// search item button
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