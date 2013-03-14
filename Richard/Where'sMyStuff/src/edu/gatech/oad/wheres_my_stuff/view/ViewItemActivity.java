package edu.gatech.oad.wheres_my_stuff.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.oad.wheres_my_stuff.R;
import edu.gatech.oad.wheres_my_stuff.model.Database2;
import edu.gatech.oad.wheres_my_stuff.model.MyItem;

/**
 * Views a specific item's details
 * 
 * @author Richard
 */
public class ViewItemActivity extends Activity 
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_item_layout);
		
		final TextView typeText = (TextView) findViewById(R.id.type_text);
		final TextView nameText= (TextView) findViewById(R.id.name_text);
		final TextView descriptionText = 
				(TextView) findViewById(R.id.description_text);
		final TextView categoryText = 
				(TextView) findViewById(R.id.category_text);
		final TextView locationText = 
				(TextView) findViewById(R.id.location_text);
		final TextView rewardText = 
				(TextView) findViewById(R.id.reward_text);
		final TextView dateText = (TextView) findViewById(R.id.date_text);
		final ImageView itemImage = (ImageView) findViewById(R.id.item_image);
		
		if(getIntent().getExtras() == null) finish();
		String itemName = (String) getIntent().getExtras().get("itemName");
		MyItem item = Database2.getItem(itemName);
		typeText.setText(item.getAttribute(MyItem.Attribute.TYPE));
		nameText.setText(item.getAttribute(MyItem.Attribute.NAME));
		descriptionText.setText(
				item.getAttribute(MyItem.Attribute.DESCRIPTION));
		categoryText.setText(item.getAttribute(MyItem.Attribute.CATEGORY));
		locationText.setText(item.getAttribute(MyItem.Attribute.LOCATION));
		rewardText.setText(item.getAttribute(MyItem.Attribute.REWARD));
		dateText.setText(item.getAttribute(MyItem.Attribute.DATE));
		itemImage.setImageBitmap(item.getImage());
	}
}
