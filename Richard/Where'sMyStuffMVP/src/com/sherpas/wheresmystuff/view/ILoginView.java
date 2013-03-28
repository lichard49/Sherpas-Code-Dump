package com.sherpas.wheresmystuff.view;

import com.sherpas.wheresmystuff.model.Person;

/**
 * Login view's interaction with the outside world
 * 
 * @author Richard
 *
 */
public interface ILoginView 
{
	void toHomeActivity(Person p);
	void toSignupActivity();
	void failedAdvance(String error);
}
