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

public class LoginActivity extends Activity {
	

		/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("YourTag", "YourOutput");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.another_file);

		/* Set OnClickListner to the login button */
		ImageButton login = (ImageButton) findViewById(R.id.Login_Button);

		//final EditText firstName = (EditText) findViewById(R.id.first_name);
		//final EditText lastName = (EditText) findViewById(R.id.last_name);
		final EditText loginName = (EditText) findViewById(R.id.username);
		final EditText password = (EditText) findViewById(R.id.password);
		
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Log.d("YourTag", "YourOutputa");
				/* Get user Name */
				
				String email = loginName.getText().toString();		
				String pw = password.getText().toString();
				//String first = firstName.getText().toString();
				//String last = lastName.getText().toString();
				
				Log.e("Login", email);
				Log.e("Password", pw);
				
				if(validUser(new Person("", "", email, pw)))
				{
					Log.d("YourTag", "YourOutputab");
					
					Database.loggedIn = new Person("", "", email, pw);
					
					/* create Intent and set LoginSuccess Activity */
					Intent intent = new Intent(getApplicationContext(),	LoginSuccessActivity.class);
					Toast.makeText(getApplicationContext(),"Credentials Correct",Toast.LENGTH_LONG).show();
					/* Start LoginSuccess Activity */
					startActivity(intent);
					finish();
				}
				else 
					Toast.makeText(getApplicationContext(),"Credentials Incorrect",Toast.LENGTH_LONG).show();
				
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
	
	Person person;
	boolean valid;
	private boolean validUser(Person p) 
	{
		// TODO Auto-generated method stub
		//Log.e("Check",Database.map.get("at.com").getFirstName());
		/*
		boolean flag=Database.map.containsKey(name);
		if(flag)
			return password.equals(Database.map.get(name));
			*/
		person = p;
		new Thread()
		{
			public void run()
			{
				valid = Database.getInstance().emailExists(person.getEmail());
				System.out.println("THIS IS : " + valid);
			}
		}.start();
		try { Thread.sleep(1000); } catch (Exception e) { }
		return valid; //Database.map.containsKey(p.getEmail()) && p.getPassword().equals(Database.map.get(p.getEmail()).getPassword());
	}
	
}