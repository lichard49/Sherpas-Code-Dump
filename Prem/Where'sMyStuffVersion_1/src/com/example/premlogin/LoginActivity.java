package com.example.premlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {
	

		/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("YourTag", "YourOutput");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.another_file);

		/* Set OnClickListner to the login button */
		ImageButton login = (ImageButton) findViewById(R.id.Login_Button);

		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Log.d("YourTag", "YourOutputa");
				/* Get user Name */
				EditText loginName = (EditText) findViewById(R.id.username);
				String name = loginName.getText().toString();
				EditText password = (EditText) findViewById(R.id.password);
				String pw = password.getText().toString();
				
				Log.e("Login", name);
				Log.e("Password", pw);
				
				if(validUser(name,pw))
				{
					Log.d("YourTag", "YourOutputab");
					/* create Intent and set LoginSuccess Activity */
					Intent intent = new Intent(getApplicationContext(),	LoginSuccess.class);
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
					Intent intent = new Intent(getApplicationContext(),	RegisterAccount.class);
					/* Start LoginSuccess Activity */
					startActivity(intent);
					
					Log.e("Register", "clicked");
					
				}
			});
			
	}
	
	private boolean validUser(String name,String password) {
		// TODO Auto-generated method stub
		Log.e("Check",Database.map.get("at.com").getFirstName());
		boolean flag=Database.map.containsKey(name);
		if(flag)
			return password.equals(Database.map.get(name));
		return false;
	}
	
}



