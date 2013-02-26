package com.example.premlogin;

import com.example.premlogin.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAccount extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView txt_registerNow	=	(TextView) findViewById(R.id.Signup_Button);
        /* Get user Name */
		
		
		Button signUp = (Button) findViewById(R.id.btn_login);

		signUp.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				EditText loginName = (EditText) findViewById(R.id.editText5);
				String userName = loginName.getText().toString();
				EditText password = (EditText) findViewById(R.id.editText1);
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
