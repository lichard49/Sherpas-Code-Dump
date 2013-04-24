package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.presenter.LoginPresenter;

/**
 * The login page consisting of two text fields for the user to submit
 * login credentials
 * 
 * @author Richard
 *
 */
public class LoginActivity extends Activity 
	implements ILoginView, OnClickListener
{
	private LoginPresenter presenter;
	
	private EditText usernameField;
	private EditText passwordField;
	
	private ImageButton loginButton;
	private ImageButton signupButton;
	
	/**
	 * Called when the activity is first created, gives functionality to each
	 * of the widgets
	 * 
	 * @param savedInstanceState the state of the Activity at the end of the
	 * last session
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		presenter = new LoginPresenter(this, DatabaseModel.getInstance());
		
		usernameField = (EditText) findViewById(R.id.username);
		passwordField = (EditText) findViewById(R.id.password);
		
		loginButton = (ImageButton) findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
		signupButton = (ImageButton) findViewById(R.id.signup_button);
		signupButton.setOnClickListener(this);
	}

	/**
	 * Successful registration starts the home page
	 * 
	 * @param p Person to be logged in as
	 */
	@Override
	public void toHomeActivity(Person p) 
	{
		Toast.makeText(getApplicationContext(), 
				"Credentials correct, logging in...", 
				Toast.LENGTH_SHORT).show();
		Person.setLoggedInUser(p, getApplication());
		Intent toHome = new Intent(this, HomeActivity.class);
		startActivity(toHome);

	}
	
	/**
	 * Request to sign up starts the registration page
	 */
	@Override
	public void toSignupActivity()
	{
		Intent toSignup = new Intent(this, SignupActivity.class);
		startActivity(toSignup);
	}

	/**
	 * An error in logging in
	 * 
	 * @param error the cause of the error
	 */
	@Override
	public void failedAdvance(String error) 
	{
		Toast.makeText(getApplicationContext(), 
				error, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Called when either the login or the sign up buttons are pressed
	 * 
	 * @param v source of the click
	 */
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.login_button:
			presenter.onLoginClick(usernameField.getText().toString(),
					passwordField.getText().toString());
			break;
		case R.id.signup_button:
			presenter.onSignupClick();
			break;
		}
	}
}
