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

public class LoginActivity extends Activity 
{
	private int failedLoginCount = 0;
	private boolean locked = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		Log.d("YourTag", "YourOutput");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.another_file);

		/* Set OnClickListner to the login button */
		ImageButton login = (ImageButton) findViewById(R.id.Login_Button);

		final EditText loginName = (EditText) findViewById(R.id.username);
		final EditText password = (EditText) findViewById(R.id.password);
		
		login.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View v) {
				Log.d("YourTag", "YourOutputa");
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
					if(validUser(new Person("", "", email, pw)))
					
					{
						
						Log.d("YourTag", "YourOutputab");
						
						Database.loggedIn = new Person("", "", email, pw);
						
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
						if(failedLoginCount > 2) locked = true;
						Toast.makeText(getApplicationContext(),"Credentials Incorrect " + failedLoginCount,Toast.LENGTH_SHORT).show();
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
		// TODO Auto-generated method stub
		//Log.e("Check",Database.map.get("at.com").getFirstName());
		/*
		boolean flag=Database.map.containsKey(name);
		if(flag)
			return password.equals(Database.map.get(name));
			*/
		return Database.map.containsKey(p.getEmail()) && p.getPassword().equals(Database.map.get(p.getEmail()).getPassword());
	}
	
}