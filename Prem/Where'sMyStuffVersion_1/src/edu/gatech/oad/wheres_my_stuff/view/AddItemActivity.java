package edu.gatech.oad.wheres_my_stuff.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database;
import edu.gatech.oad.wheres_my_stuff.model.MyItem;
import edu.gatech.oad.wheres_my_stuff.model.Person;

/**
 * The Activity that handles the adding of details about an item as well as 
 * ensuring those details are stored properly for retrieval later.
 * 
 * @author Richard
 *
 */

public class AddItemActivity extends Activity 
{
	private ImageView itemImage;
	private MyItem newItem;
	
	private Intent takePicture;
	private Intent toSearchActivity;

	/**
	 * Instantiating the widgets and giving them functionality.
	 * 
	 * @param savedInstanceState the state of the Activity before onPause()
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item_layout);
		
		final Spinner typeSpinner = (Spinner) findViewById(R.id.type_spinner);
		final EditText nameTextField = (EditText) findViewById(R.id.name_text_field);
		final EditText descriptionTextField = (EditText) findViewById(R.id.description_text_field);
		final Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		final EditText locationTextField = (EditText) findViewById(R.id.location_text_field);
		final EditText rewardTextField = (EditText) findViewById(R.id.reward_text_field);
		final EditText dateTextField = (EditText) findViewById(R.id.date_text_field);
		itemImage = (ImageView) findViewById(R.id.item_image);
		
		newItem = new MyItem(Person.getLoggedInUser(getApplicationContext()));
		toSearchActivity = new Intent(AddItemActivity.this, SearchActivity.class);
		final ImageButton submitItemButton = (ImageButton) findViewById(R.id.submit_button);
		submitItemButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				if(nameTextField.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please fill in a name for this item", 
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					newItem.addAttribute(MyItem.Attribute.TYPE, (String) typeSpinner.getSelectedItem());
					newItem.addAttribute(MyItem.Attribute.NAME, nameTextField.getText().toString());
					newItem.addAttribute(MyItem.Attribute.DESCRIPTION, descriptionTextField.getText().toString());
					newItem.addAttribute(MyItem.Attribute.CATEGORY, (String) categorySpinner.getSelectedItem());
					newItem.addAttribute(MyItem.Attribute.LOCATION, locationTextField.getText().toString());
					newItem.addAttribute(MyItem.Attribute.REWARD, rewardTextField.getText().toString());
					newItem.addAttribute(MyItem.Attribute.DATE, dateTextField.getText().toString());
					Database.addItem(newItem);
					startActivity(toSearchActivity);
					finish();
				}
			}
		});
		
		if(savedInstanceState != null)
		{
			Bitmap image = (Bitmap) savedInstanceState.getParcelable("image");
			itemImage.setImageBitmap(image);
			newItem.setImage(image);
		}
		takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		final Button takePictureButton = (Button) findViewById(R.id.picture_button);
		takePictureButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				startActivityForResult(takePicture, 0);
			}
		});
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
			newItem.setImage(image);
		}
	}
	
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putParcelable("image", newItem.getImage());
	}
}
