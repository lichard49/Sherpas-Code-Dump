package com.sherpas.wheresmystuff.presenter;

import com.sherpas.wheresmystuff.command.AddUserCommand;
import com.sherpas.wheresmystuff.command.CommandProcessor;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.view.ISignupView;

/**
 * Presenter that acts as the intermediary between view and model
 * 
 * @author Richard
 *
 */
public class SignupPresenter 
{
	private final ISignupView view;
	private final IDatabaseModel model;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public SignupPresenter(ISignupView v, IDatabaseModel m)
	{
		view = v;
		model = m;
	}
	
	/**
	 * Creates a new user
	 * 
	 * @param fn first name
	 * @param ln last name
	 * @param e email
	 * @param ph phone
	 * @param pw password
	 */
	public Person onSignupClick(String fn, String ln, String e, String ph,
			String pw)
	{
		AddUserCommand add = new AddUserCommand(fn, ln, e, ph, pw, false,
				DatabaseModel.getInstance());
		CommandProcessor.getInstance().execute(add);
		//Person personCreated= model.createUser(fn, ln, e, ph, pw, false);
		Person personCreated = add.getAddedUser();
		// TODO: handle invalid information
		System.out.println("Created user: " +personCreated.getFirstName());
		view.toLoginActivity();
		return personCreated;
	}
}
