package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.presenter.SignupPresenter;

/**
 * Register account page that gives the user text fields to input personal
 * information and create an account.
 * 
 * @author Richard
 *
 */
public class SignupActivity extends Activity
	implements ISignupView, OnClickListener
{
	private SignupPresenter presenter;
	
	private ImageButton signupButton;
	private EditText firstName;
	private EditText lastName;
	private EditText email;
	private EditText phone;
	private EditText password;
	
	/**
	 * Called when the activity is first created, gives functionality to each
	 * of the widgets
	 * 
	 * @param savedInstanceState the state of the Activity at the end of the
	 * last session
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_layout);
		
		presenter = new SignupPresenter(this, DatabaseModel.getInstance());
		
		signupButton = (ImageButton) findViewById(R.id.signup_button);
		signupButton.setOnClickListener(this);
		
		firstName = (EditText) findViewById(R.id.first_name);
		lastName = (EditText) findViewById(R.id.last_name);
		email = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.phone_number);
		password = (EditText) findViewById(R.id.password);
	}
	
	/**
	 * Move to login activity after a successful registration
	 */
	@Override
	public void toLoginActivity() 
	{
		Toast.makeText(getApplicationContext(), "Successful Registration",
				Toast.LENGTH_SHORT).show();
		finish();
	}

	/**
	 * Called when the sign up button is clicked
	 * 
	 * @param v source of the click
	 */
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.signup_button:
			presenter.onSignupClick(
					firstName.getText().toString(),
					lastName.getText().toString(),
					email.getText().toString(),
					phone.getText().toString(),
					password.getText().toString());
			break;
		}
	}
}
