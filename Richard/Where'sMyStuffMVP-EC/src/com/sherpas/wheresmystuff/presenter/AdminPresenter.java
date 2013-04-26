package com.sherpas.wheresmystuff.presenter;

import java.util.List;

import android.content.Context;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.support.PersonAdapter;
import com.sherpas.wheresmystuff.view.IAdminView;

public class AdminPresenter
{
	private enum Action
	{
		MAKE_ADMIN, DELETE, LOCK;
	}
	
	private final IAdminView view;
	private final IDatabaseModel model;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public AdminPresenter(IAdminView v, IDatabaseModel m)
	{
		view = v;
		model = m;
	}
	
	public PersonAdapter populatePersonAdapter(Context c)
	{
		List<Person> list = model.getAllUsers();
		System.out.println("List: " + list);
		Person[] people = new Person[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			people[i] = list.get(i);
		}
		return new PersonAdapter(c, R.layout.admin_item_layout, people);
	}
	
	public void onLockClick(String email, Person loggedIn)
	{
		performAction(Action.LOCK, email, loggedIn);
	}
	
	public void onMakeAdminClick(String email, Person loggedIn)
	{
		performAction(Action.MAKE_ADMIN, email, loggedIn);
	}
	
	public void onDeleteClick(String email, Person loggedIn)
	{
		performAction(Action.DELETE, email, loggedIn);
	}
	
	private void performAction(Action a, String email, Person loggedIn)
	{
		if(!sameEmail(email, loggedIn.getEmail()))
		{
			view.toastMessage("Action has been performed, refreshing...");
			switch(a)
			{
			case DELETE:
				if(!sameEmail("admin", loggedIn.getEmail()))
					model.deleteUser(email);
				else
					view.toastMessage("You cannot delete the root admin!");
				break;
			case MAKE_ADMIN:
				model.setAdmin(email, true);
				break;
			case LOCK:
				model.setLocked(email, !model.emailLocked(email));
				break;
			}
			view.refresh();
		}
		else
		{
			view.toastMessage("You cannot perform an action on your own account!");
		}
	}
	
	private boolean sameEmail(String email1, String email2)
	{
		return email1.equals(email2);
	}
}
