package com.sherpas.wheresmystuff.view;


/**
 * Login view's interaction with the outside world
 * 
 * @author Richard
 *
 */
public interface ILoginView 
{
	void toHomeActivity();
	void toSignupActivity();
	void failedAdvance(String error);
}
