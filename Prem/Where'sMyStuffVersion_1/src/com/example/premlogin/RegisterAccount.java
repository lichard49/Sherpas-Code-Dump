package com.example.premlogin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAccount extends Activity 
{

	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        
		final Button signUp = (Button) findViewById(R.id.signup_button);
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		final EditText firstName = (EditText) findViewById(R.id.first_name);
		final EditText lastName = (EditText) findViewById(R.id.last_name);
		final TextView registerTitle = (TextView) findViewById(R.id.register_title);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/bauhaus_93.ttf");
		registerTitle.setTypeface(font);
		
		signUp.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{	
				String userName = email.getText().toString();
				
				String pw = password.getText().toString();
				
				String fn=firstName.getText().toString();
				
				String ln=lastName.getText().toString();
				
				if(!(Database.map.containsKey(userName)))
				{
						Log.e("UserName(Email)", userName);
						Log.e("Password", pw);
						Database.map.put(userName, new Person(fn,ln,userName, pw));
						
						Intent intent = new Intent(getApplicationContext(),	RegisterAccount.class);
						/* Start LoginSuccess Activity */
						startActivity(intent);
						
						Log.e("Register", "clicked");
						Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
						/* Start LoginSuccess Activity */
						startActivity(loginIntent);
						Toast.makeText(getApplicationContext(),"Thank you for Registering",Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),"That Username is taken. Sorry!",Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
