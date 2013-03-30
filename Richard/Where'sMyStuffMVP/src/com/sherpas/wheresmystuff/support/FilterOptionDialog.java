package com.sherpas.wheresmystuff.support;

import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.sherpas.wheresmystuff.R;

public class FilterOptionDialog extends Dialog
{
	private CheckBox type;
	private CheckBox heirloom;
	private CheckBox keepsake;
	private CheckBox misc;
	private CheckBox category;
	private CheckBox lost;
	private CheckBox found;
	private CheckBox donation;
	private CheckBox date;
	private DatePicker startDate;
	private ImageButton filterFinish;
	private Bundle filters;
	private WindowManager.LayoutParams lp;
	
	public FilterOptionDialog(Context context, Bundle filters)
	{
		super(context);
		setTitle("Filter options:");
		setContentView(R.layout.filter_option_layout);
		
		lp = new WindowManager.LayoutParams();
	    lp.copyFrom(getWindow().getAttributes());
	    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
	    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
		this.setOnShowListener(new OnShowListener()
		{
			@Override
			public void onShow(DialogInterface d) 
			{
				getWindow().setAttributes(lp);
			}
		});
		
		type = (CheckBox) findViewById(R.id.type_box);
		heirloom = (CheckBox) findViewById(R.id.heirloom_box);
		keepsake = (CheckBox) findViewById(R.id.keepsake_box);
		misc = (CheckBox) findViewById(R.id.misc_box);
		category = (CheckBox) findViewById(R.id.category_box);
		lost = (CheckBox) findViewById(R.id.lost_box);
		found = (CheckBox) findViewById(R.id.found_box);
		donation = (CheckBox) findViewById(R.id.donation_box);
		date = (CheckBox) findViewById(R.id.date_box);
		startDate = (DatePicker) findViewById(R.id.start_date);
		filterFinish = (ImageButton) findViewById(R.id.filter_finish);
		filterFinish.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				closeDialog();
			}
		});
		
		this.filters = filters;
	}
	
	public void closeDialog()
	{
		if(type.isChecked())
		{
			filters.putString("big", "type");
			if(heirloom.isChecked())
			{
				filters.putString("type", "Heirloom");
			}
			else if(keepsake.isChecked())
			{
				filters.putString("type", "Keepsake");
			}
			else if(misc.isChecked())
			{
				filters.putString("type", "Miscellaneous");
			}
		}
		else if(category.isChecked())
		{
			filters.putString("big", "category");
			if(lost.isChecked())
			{
				filters.putString("category", "Lost");
			}
			else if(found.isChecked())
			{
				filters.putString("category", "Found");
			}
			else if(donation.isChecked())
			{
				filters.putString("category", "Donation");
			}
		}
		else if(date.isChecked())
		{
			filters.putString("big", "date");
			filters.putSerializable("date", new Date(startDate.getYear()-1900,
					startDate.getMonth(), startDate.getDayOfMonth()));
		}
		cancel();
	}
}
