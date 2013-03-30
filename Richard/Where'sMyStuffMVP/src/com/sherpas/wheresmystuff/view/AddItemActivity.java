package com.sherpas.wheresmystuff.view;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
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
	
	private ImageView itemImage;
	private Bitmap image;
	
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Add camera functionality
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item_layout);
		
		presenter = new AddItemPresenter(this, DatabaseModel.getInstance());
		
		typeSpinner = (Spinner) findViewById(R.id.type_spinner);
		nameTextField = (EditText) findViewById(R.id.name_text_field);
		descriptionTextField= (EditText) findViewById(R.id.description_text_field);
		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		locationTextField = (EditText) findViewById(R.id.location_text_field);
		rewardTextField = (EditText) findViewById(R.id.reward_text_field);
		datePicker = (DatePicker) findViewById(R.id.date_picker);
		
		itemImage = (ImageView) findViewById(R.id.item_image);
		// restore picture from previous session if applicable
		if(savedInstanceState != null)
		{
			Bitmap image = (Bitmap) savedInstanceState.getParcelable("image");
			itemImage.setImageBitmap(image);
			this.image = image;
		}
		
		final ImageButton pictureButton = (ImageButton) findViewById(R.id.picture_button);
		pictureButton.setOnClickListener(this);
		
		final ImageButton submitButton = (ImageButton) findViewById(R.id.submit_button);
		submitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.picture_button:
			presenter.startCamera();
			break;
		case R.id.submit_button:
			presenter.submitItem(typeSpinner.getSelectedItem().toString(),
					nameTextField.getText().toString(),
					descriptionTextField.getText().toString(),
					categorySpinner.getSelectedItem().toString(),
					locationTextField.getText().toString(),
					rewardTextField.getText().toString(),
					new Date(datePicker.getYear()-1900, datePicker.getMonth(),
							datePicker.getDayOfMonth()),
					itemImage.getDrawingCache(),
					Person.getLoggedInUser(getApplicationContext()));
			break;
		}
	}

	/**
	 * Overriden method to handle returning from taking a picture by making
	 * the picture visible.
	 * 
	 * @param requestCode the action requested
	 * @param resultCode the state after the action
	 * @param data information passed back
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		//TODO: make it load after onPause also
		if(data != null && data.getExtras() != null && 
				data.getExtras().get("data") != null)
		{
			Bitmap image = (Bitmap) data.getExtras().get("data");
			itemImage.setImageBitmap(image);
			this.image = image;
		}
	}
	
	/**
	 * Called when the Activity is changing states to ensure persistence
	 * between sessions
	 * 
	 * @param savedInstanceState the information to store
	 */
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		if(image != null)
		savedInstanceState.putParcelable("image", image);
	}
	
	@Override
	public void toHomeActivity()
	{
		Intent toHome = new Intent(this, HomeActivity.class);
		startActivity(toHome);
	}

	@Override
	public void toTakePicture()
	{
		Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePicture, 0);
	}
}
