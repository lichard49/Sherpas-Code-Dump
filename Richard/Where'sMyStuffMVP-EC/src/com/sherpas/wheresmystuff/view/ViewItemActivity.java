package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DBItem;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.presenter.ViewItemPresenter;
import com.sherpas.wheresmystuff.support.ItemAdapter;

/**
 * Views a specific item's details
 * 
 * @author Richard
 */
public class ViewItemActivity extends Activity implements IViewItemView
{
	private ViewItemPresenter presenter;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_item_layout);
		
		presenter = new ViewItemPresenter(this, DatabaseModel.getInstance());
		
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
		final ListView matchList = (ListView) findViewById(R.id.match_list);
		final ImageView itemImage = (ImageView) findViewById(R.id.item_image);
		
		if(getIntent().getExtras() == null) finish();
		String itemName = (String) getIntent().getExtras().get("itemName");
		
		
		
		System.out.println("Item name: " + itemName);
		DBItem item = presenter.getItem(itemName);
		if(item == null)
		{
			System.out.println("NULLLL");
			finish();
		}
		else
		{
			matchList.setAdapter(new ItemAdapter(this, 
					R.layout.search_item_layout, 
					presenter.getSameNameItem(item)));
			System.out.println(item.getCategoryID());
			typeText.setText(presenter.getType(item.getTypeID()));
			nameText.setText(item.getName());
			descriptionText.setText(item.getDescription());
			categoryText.setText(presenter.getCategory(item.getCategoryID()));
			locationText.setText("here");
			rewardText.setText("$" + item.getReward());
			dateText.setText(item.getDatePosted()+"");
			try
			{
				itemImage.setImageBitmap(presenter.getImage(item).get(0).getImage());
			}
			catch (Exception e)
			{
			}
		}
		
		/*
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
		*/
		
	}
}

