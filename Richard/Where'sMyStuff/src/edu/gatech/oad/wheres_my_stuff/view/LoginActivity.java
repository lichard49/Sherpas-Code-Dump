package edu.gatech.oad.wheres_my_stuff.view;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;
import edu.gatech.oad.wheres_my_stuff.model.Person;

/**
 * The login page consisting of two text fields for the user to submit
 * information 
 * 
 * @author Richard
 *
 */
public class LoginActivity extends Activity 
{
	private Map<String, Integer> numFailedTries;

	/** 
	 * Called when the activity is first created
	 * 
	 * @param savedInstanceState the state of the Activity at the end of the
	 * last session
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		numFailedTries = new HashMap<String, Integer>();
		
		/* Set OnClickListner to the login button */
		ImageButton login = (ImageButton) findViewById(R.id.Login_Button);

		final EditText loginName = (EditText) findViewById(R.id.username);
		final EditText password = (EditText) findViewById(R.id.password);
		
		login.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View v) {
				/* Get user Name */
				
				String email = loginName.getText().toString();		
				String pw = password.getText().toString();
				
				Log.e("Login", email);
				Log.e("Password", pw);
				
				Person attemptedUser = validUser(email, pw);
				//call email exists to check to see if connectin error
				if(attemptedUser != null)
				{
					Person.setLoggedInUser(attemptedUser, 
							getApplicationContext());
					
					if(attemptedUser.isLocked())
					{
						
					}
					else
					{
						/* create Intent and set LoginSuccess Activity */
						Intent intent = new Intent(getApplicationContext(),	
								LoginSuccessActivity.class);
						Toast.makeText(getApplicationContext(),
								"Credentials Correct",
								Toast.LENGTH_SHORT).show();
						/* Start LoginSuccess Activity */
						startActivity(intent);
						finish();
					}
				}
				else if(Database.getInstance().emailExists(email))
				{
					if(!numFailedTries.containsKey(email)) 
						numFailedTries.put(email, 0);
					
					int tries = numFailedTries.get(email);
					if(tries > 1 || Database.getInstance().emailLocked(email))
					{
						Database.getInstance().setLocked(email, true);
						Toast.makeText(getApplicationContext(), 
								"Your account has been locked. " +
								"Please contact an admin.", 
								Toast.LENGTH_SHORT).show();						
					}
					else
					{
						numFailedTries.put(email, 
								numFailedTries.get(email)+1);
						Toast.makeText(getApplicationContext(), 
								"Credentials Incorrect #" + tries, 
								Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), 
							"Connection error",Toast.LENGTH_SHORT).show();
				}
			}
			
		});

		ImageButton register = (ImageButton) findViewById(R.id.signup_button);
		register.setOnClickListener(new View.OnClickListener() 
		{
			/**
			 * Called when the register button is pressed, and opens the
			 * register page
			 * 
			 * @param v the view pressed
			 */
			public void onClick(View v) 
			{
				Intent intent = new Intent(getApplicationContext(),	
						RegisterAccountActivity.class);
				
				/* Start LoginSuccess Activity */
				startActivity(intent);
				
				Log.e("Register", "clicked");
				
			}
		});
	}
	
	/**
	 * Authenticates a given email and password pair
	 * 
	 * @param email email to try
	 * @param password password to try
	 * @return if a the pair is correct, the Person object associated with the
	 * pair, otherwise null
	 */
	private Person validUser(String email, String password) 
	{
		return Database.getInstance().authenticate(email, password);
	}
}