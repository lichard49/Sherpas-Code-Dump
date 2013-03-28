package com.sherpas.wheresmystuff.view;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.model.TempDatabaseModel;
import com.sherpas.wheresmystuff.presenter.AddItemPresenter;

public class AddItemActivity extends Activity implements IAddItemView, OnClickListener
{
	private AddItemPresenter presenter;
	
	private Spinner typeSpinner;
	private EditText nameTextField;
	private EditText descriptionTextField;
	private Spinner categorySpinner;
	private EditText locationTextField;
	private EditText rewardTextField;
	private DatePicker datePicker;
	
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Add camera functionality
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item_layout);
		
		// TODO: This needs to be updated to the real database once it accounts for items
		presenter = new AddItemPresenter(this, TempDatabaseModel.getInstance());
		
		typeSpinner = (Spinner) findViewById(R.id.type_spinner);
		nameTextField = (EditText) findViewById(R.id.name_text_field);
		descriptionTextField= (EditText) findViewById(R.id.description_text_field);
		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		locationTextField = (EditText) findViewById(R.id.location_text_field);
		rewardTextField = (EditText) findViewById(R.id.reward_text_field);
		datePicker = (DatePicker) findViewById(R.id.date_picker);
		
		final ImageButton submitButton = (ImageButton) findViewById(R.id.submit_button);
		submitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.submit_button:
			presenter.submitItem(typeSpinner.getSelectedItem().toString(),
					nameTextField.getText().toString(),
					descriptionTextField.getText().toString(),
					categorySpinner.getSelectedItem().toString(),
					locationTextField.getText().toString(),
					rewardTextField.getText().toString(),
					new Date(datePicker.getYear()-1900, datePicker.getMonth(),
							datePicker.getDayOfMonth()),
					Person.getLoggedInUser(getApplicationContext()));
			break;
		}
	}

	@Override
	public void toHomeActivity()
	{
		Intent toHome = new Intent(this, HomeActivity.class);
		startActivity(toHome);
	}
}
