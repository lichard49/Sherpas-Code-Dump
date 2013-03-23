package edu.gatech.oad.wheres_my_stuff.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;

/**
 * Register account page that gives the user text fields to input personal
 * information and create an account.
 * 
 * @author Richard
 *
 */
public class RegisterAccountActivity extends Activity 
{
	private final String expression = 
			"^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	
	/**
	 * Called when the activity is first created, gives functionality to each
	 * of the text fields
	 * 
	 * @param savedInstanceState the state of the Activity at the end of the
	 * last session
	 */
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        
		// header with font
		final TextView registerTitle = 
				(TextView) findViewById(R.id.register_title);
		Typeface font = Typeface.createFromAsset(getAssets(), 
				"fonts/bauhaus_93.ttf");
		registerTitle.setTypeface(font);
		
		// text fields
		final Button signUp = (Button) findViewById(R.id.signup_button);
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		final EditText firstName = (EditText) findViewById(R.id.first_name);
		final EditText lastName = (EditText) findViewById(R.id.last_name);
		
		signUp.setOnClickListener(new View.OnClickListener() 
		{
			/**
			 * Called when the sign up button is pressed, validates each of
			 * the text fields
			 * 
			 * @param v the pressed view
			 */
			public void onClick(View v) 
			{	
				String userName = email.getText().toString();
				String pw = password.getText().toString();
				String fn = firstName.getText().toString();
				String ln = lastName.getText().toString();
				
				if(userName.equals("") || pw.equals(""))
				{
					Toast.makeText(getApplicationContext(),
							"Please fill out the required fields",
							Toast.LENGTH_SHORT).show();
				}
				else if(!Database.getInstance().emailExists(userName))
				{
						Log.e("UserName(Email)", userName);
						Log.e("Password", pw);
						Database.getInstance().createUser(fn, ln, userName,
								"8", pw, false);
						
						Intent intent = new Intent(getApplicationContext(),
								RegisterAccountActivity.class);
						/* Start LoginSuccess Activity */
						startActivity(intent);
						
						Log.e("Register", "clicked");
						Intent loginIntent = new Intent(
								getApplicationContext(),
								LoginActivity.class);
						/* Start LoginSuccess Activity */
						startActivity(loginIntent);
						Toast.makeText(getApplicationContext(),
								"Thank you for Registering",
								Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"That Username is taken. Sorry!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		email.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			/**
			 * Live validation of the email text field to ensure proper
			 * formatting of the email
			 * 
			 * @param v the v being changed
			 * @param hasFocus if the view has the focus
			 */
			public void onFocusChange(View v, boolean hasFocus) 
			{
				if(!hasFocus && !isValidEmail(email.getText().toString()))
				{
					Toast.makeText(getApplicationContext(),"Enter a valid email, please.",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/**
	 * Helper for the validation of an email
	 * 
	 * @param email the String to test
	 * @return true if the email is valid, false otherwise
	 */
	private boolean isValidEmail(String email)
	{
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
