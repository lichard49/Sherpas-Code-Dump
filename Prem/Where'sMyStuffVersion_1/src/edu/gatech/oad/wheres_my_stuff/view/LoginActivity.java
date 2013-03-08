package edu.gatech.oad.wheres_my_stuff.view;

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
 * 
 * 
 * @author Richard
 *
 */
public class LoginActivity extends Activity 
{
	private int failedLoginCount = 0;
	private boolean locked = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

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
				
				if(locked)
				{
					Toast.makeText(getApplicationContext(),"Your account has been locked, " +
							"please contact the administrator",Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(validUser(new Person("", "", email, pw, false)))
					{
						Person dummy = new Person("", "", email, pw, false);
						Person dummyWithDetails = Database.getPerson(dummy.getEmail());
						Person.setLoggedInUser(dummyWithDetails, getApplicationContext());
						
						/* create Intent and set LoginSuccess Activity */
						Intent intent = new Intent(getApplicationContext(),	LoginSuccessActivity.class);
						Toast.makeText(getApplicationContext(),"Credentials Correct",Toast.LENGTH_SHORT).show();
						/* Start LoginSuccess Activity */
						startActivity(intent);
						finish();
					}
					else
					{
						failedLoginCount++;
						if(failedLoginCount > 1) locked = true;
						Toast.makeText(getApplicationContext(),"Credentials Incorrect #" + failedLoginCount,Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});

		ImageButton register = (ImageButton) findViewById(R.id.signup_button);
		register.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),	RegisterAccountActivity.class);
				/* Start LoginSuccess Activity */
				startActivity(intent);
				
				Log.e("Register", "clicked");
				
			}
		});
	}
	
	private boolean validUser(Person p) 
	{
		return Database.getPerson(p.getEmail())!=null && p.getPassword().equals(Database.getPerson(p.getEmail()).getPassword());
	}
}