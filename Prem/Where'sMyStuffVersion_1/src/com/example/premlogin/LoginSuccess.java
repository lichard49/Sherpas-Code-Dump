package com.example.premlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccess extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        
        TextView txt_loggedName	=	(TextView) findViewById(R.id.Login_Button);
	}
}
