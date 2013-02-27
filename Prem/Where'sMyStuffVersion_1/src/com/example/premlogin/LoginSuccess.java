package com.example.premlogin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccess extends Activity {

	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        
        final TextView greeting = (TextView) findViewById(R.id.greeting);
        String name = Database.map.get(Database.loggedIn.getEmail()).getFirstName();
        greeting.setText("Welcome, " + name + "!");
	}
}