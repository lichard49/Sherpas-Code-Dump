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
import edu.gatech.oad.wheres_my_stuff.model.Person;

public class RegisterAccountActivity extends Activity 
{
	private final String expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	
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
						
						Intent intent = new Intent(getApplicationContext(),	RegisterAccountActivity.class);
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
		
		email.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			public void onFocusChange(View v, boolean hasFocus) 
			{
				if(!hasFocus && !isValidEmail(email.getText().toString()))
				{
					Toast.makeText(getApplicationContext(),"Enter a valid email, please.",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private boolean isValidEmail(String email)
	{
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
