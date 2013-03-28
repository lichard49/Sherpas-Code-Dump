package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.presenter.AdminPresenter;

public class AdminActivity extends Activity implements IAdminView
{
	private AdminPresenter presenter;
	private ListView list;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_layout);
		
		presenter = new AdminPresenter(this, DatabaseModel.getInstance());
		
		list = (ListView) findViewById(R.id.list_of_users);
		list.setAdapter(presenter.populatePersonAdapter(this));
	}

	/**
	 * Called when a lock button is pressed, the appropriate user is locked
	 * 
	 * @param v View that was pressed
	 */
	public void lockButtonHandler(View v) 
	{
		String pressedEmail = getSelectedEmail(v);
		presenter.onLockClick(pressedEmail, Person.getLoggedInUser(this));
	}
	
	public void makeAdminButtonHandler(View v)
	{
		String pressedEmail = getSelectedEmail(v);
		presenter.onMakeAdminClick(pressedEmail, Person.getLoggedInUser(this));
	}
	
	/**
	 * Called when a delete button is pressed, the appropriate user is deleted
	 * 
	 * @param v View that was pressed
	 */
	public void deleteButtonHandler(View v)
	{
		String pressedEmail = getSelectedEmail(v);
		presenter.onDeleteClick(pressedEmail, Person.getLoggedInUser(this));
	}
	
	/**
	 * Takes the child of a layout and looks at its parent layout in order to
	 * find the text containing its owner's name
	 * 
	 * @param child child layout to find the owner of
	 * @return owner's name
	 */
	private String getSelectedEmail(View child)
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
	 * Refreshes the Activity by killing (finishing) it and starting it again 
	 */
	public void refresh()
	{
		/*
		Intent thisIntent = getIntent();
		finish();
		startActivity(thisIntent);
		*/
		list.setAdapter(presenter.populatePersonAdapter(this));
	}
	
	public void toastMessage(String message)
	{
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
}
