package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.presenter.LoginPresenter;
import com.sherpas.wheresmystuff.support.GpsManager;

/**
 * The login page consisting of two text fields for the user to submit
 * login credentials
 * 
 * @author Richard
 *
 */
public class LoginActivity extends Activity 
	implements ILoginView, OnClickListener, AnimationListener
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
		
		GpsManager.setBaseContext(this);
		GpsManager.getInstance();
		
		presenter = new LoginPresenter(this, DatabaseModel.getInstance());
		presenter.onStartLoggedInCheck(this);
		
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
	public void toHomeActivity() 
	{
		Toast.makeText(getApplicationContext(), 
				"Credentials correct, logging in...", 
				Toast.LENGTH_SHORT).show();
		Intent toHome = new Intent(this, HomeActivity.class);
		startActivity(toHome);
		finish();
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
			Animation wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
			wobble.setAnimationListener(this);
			loginButton.startAnimation(wobble);
			presenter.onLoginClick(usernameField.getText().toString(),
					passwordField.getText().toString(), this);
			break;
		case R.id.signup_button:
			presenter.onSignupClick();
			break;
		}
	}

	@Override
	public void onAnimationEnd(Animation a)
	{
		
	}

	@Override
	public void onAnimationRepeat(Animation a)
	{
	}

	@Override
	public void onAnimationStart(Animation arg0)
	{	
	}
}
