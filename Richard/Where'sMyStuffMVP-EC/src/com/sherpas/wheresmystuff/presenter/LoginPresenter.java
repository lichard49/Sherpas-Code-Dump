package com.sherpas.wheresmystuff.presenter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.view.ILoginView;

/**
 * Presenter that acts as the intermediary between view and model,
 * presently (HEHE) handles the number of failed tries per person
 * 
 * @author Richard
 *
 */
public class LoginPresenter 
{
	private final ILoginView view;
	private final IDatabaseModel model;
	
	private Map<String, Integer> numFailedTries;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public LoginPresenter(ILoginView v, IDatabaseModel m)
	{
		view = v;
		model = m;
		
		numFailedTries = new HashMap<String, Integer>();
	}
	
	public Person auth(String username, String password)
	{
		return model.authenticate(username, password);
	}
	
	/**
	 * Checks the validity of the user's credentials and responds with the
	 * appropriate action
	 * 
	 * @param username username
	 * @param password password
	 */
	public Person onLoginClick(String username, String password, Context c)
	{
		Person temp = auth(username, password);
		if(model.emailLocked(username))
		{
			view.failedAdvance("Your account has been locked; " +
					"please contact an administrator");
		}
		else if(temp == null)
		{
			if(model.emailExists(username))
			{
				view.failedAdvance("Incorrect password");
				
				if(!username.equals(Person.ROOT_ADMIN))
				{
					if(!numFailedTries.containsKey(username))
						numFailedTries.put(username, 0);
					numFailedTries.put(username, 
							numFailedTries.get(username)+1);
					if(numFailedTries.get(username) > 2)
						model.setLocked(username, true);
				}
			}
			else
			{
				view.failedAdvance("Username does not exist");
			}
		}
		else
		{
			Person.setLoggedInUser(temp, c);
			view.toHomeActivity();
		}
		return temp;
	}
	
	/**
	 * Starts the registration page
	 */
	public void onSignupClick()
	{
		view.toSignupActivity();
	}
	
	public void onStartLoggedInCheck(Context c)
	{
		Person p = Person.getLoggedInUser(c);
		if(p != null && !"".equals(p.getEmail()))
		{
			view.toHomeActivity();
		}
	}
}
