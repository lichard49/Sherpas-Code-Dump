package com.example.premlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
		
		signUp.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{	
				String userName = email.getText().toString();
				
				String pw = password.getText().toString();
				
				Log.e("UserName(Email)", userName);
				Log.e("Password", pw);
				Database.map.put(userName, pw);
				
				Intent intent = new Intent(getApplicationContext(),	RegisterAccount.class);
				/* Start LoginSuccess Activity */
				startActivity(intent);
				
				Log.e("Register", "clicked");
				Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
				/* Start LoginSuccess Activity */
				startActivity(loginIntent);
				Toast.makeText(getApplicationContext(),"Thank you for Registering",Toast.LENGTH_LONG).show();
			}
		});
	}
}
