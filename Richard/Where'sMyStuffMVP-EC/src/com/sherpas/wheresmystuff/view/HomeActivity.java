package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.presenter.HomePresenter;

/**
 * The home screen with UI elements to navigate to the rest of the app
 * 
 * @author Richard
 *
 */
public class HomeActivity extends Activity
	implements IHomeView, OnClickListener
{
	private HomePresenter presenter;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		
		presenter = new HomePresenter(this, DatabaseModel.getInstance());
		
		// set greeting
		final TextView greeting = (TextView) findViewById(R.id.greeting);
        String name = Person.getLoggedInUser(this).
        		getFirstName();
        greeting.setText("Welcome, " + name + "!");
        
        // access to admin panel
        final Button adminPanel = (Button) findViewById(R.id.admin_panel);
        // TODO: is this correct access to Person?
        if(Person.getLoggedInUser(this).isAdmin()) 
        	adminPanel.setVisibility(Button.VISIBLE);
        adminPanel.setOnClickListener(this);
        
        final ImageButton addItemButton = (ImageButton) 
        		findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(this);
        final ImageButton favoritesButton = (ImageButton)
        		findViewById(R.id.favorites_button);
        favoritesButton.setOnClickListener(this);
        final ImageButton searchButton = (ImageButton)
        		findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        final ImageButton mailButton = (ImageButton)
        		findViewById(R.id.mail_button);
        mailButton.setOnClickListener(this);
        final ImageButton signoutButton = (ImageButton)
        		findViewById(R.id.signout_button);
        signoutButton.setOnClickListener(this);
	}

	@Override
	public void toAddItemActivity() 
	{
		System.out.println("To add item!");
		Intent toAddItem = new Intent(this, AddItemActivity.class);
		startActivity(toAddItem);
	}

	@Override
	public void toFavoritesActivity() 
	{
	}

	@Override
	public void toSearchActivity() 
	{
		Intent toSearch = new Intent(this, SearchActivity.class);
		startActivity(toSearch);
	}

	@Override
	public void toMailActivity()
	{
		
	}
	
	@Override
	public void toLoginActivity()
	{
		Intent toLogin = new Intent(this, LoginActivity.class);
		startActivity(toLogin);
		Person.logoutUser(this);
	}
	
	@Override
	public void toAdminActivity() 
	{
		Intent toAdmin = new Intent(this, AdminActivity.class);
		startActivity(toAdmin);
	}
	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.add_item_button:
			presenter.onAddItemClick();
			break;
		case R.id.favorites_button:
			presenter.onFavoritesClick();
			break;
		case R.id.search_button:
			presenter.onSearchClick();
			break;
		case R.id.mail_button:
			presenter.onMailClick();
			break;
		case R.id.admin_panel:
			presenter.onAdminClick();
			break;
		case R.id.signout_button:
			presenter.onSignoutClick();
			break;
		}
	}
}
