package edu.gatech.oad.wheres_my_stuff.view;

import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.R.id;
import edu.gatech.oad.wheres_my_stuff.R.layout;
import edu.gatech.oad.wheres_my_stuff.model.Database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        
        final TextView greeting = (TextView) findViewById(R.id.greeting);
        String name = Database.map.get(Database.loggedIn.getEmail()).getFirstName();
        greeting.setText("Welcome, " + name + "!");
        
        ImageButton addItem = (ImageButton) findViewById(R.id.Login_Button);
		addItem.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				
			}
		});
	}
}