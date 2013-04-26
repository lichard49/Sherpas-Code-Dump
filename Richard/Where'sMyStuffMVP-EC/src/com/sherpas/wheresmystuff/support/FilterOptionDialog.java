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

/**
 * A pop up dialog with options for filtering the list of shown items
 * 
 * @author Richard
 *
 */
public class FilterOptionDialog extends Dialog
{
	private CheckBox heirloom;
	private CheckBox keepsake;
	private CheckBox misc;
	private CheckBox lost;
	private CheckBox found;
	private CheckBox donation;
	private CheckBox date;
	private DatePicker startDate;
	private ImageButton filterFinish;
	private Bundle filters;
	private WindowManager.LayoutParams lp;
	
	/**
	 * Constructor 
	 * 
	 * @param context
	 * @param filters
	 */
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

		heirloom = (CheckBox) findViewById(R.id.heirloom_box);
		keepsake = (CheckBox) findViewById(R.id.keepsake_box);
		misc = (CheckBox) findViewById(R.id.misc_box);
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
	
	/**
	 * Save the information and close the dialog
	 */
	public void closeDialog()
	{
		filters.clear();
		if(lost.isChecked())
			filters.putString("type", "Lost");
		else if(found.isChecked())
			filters.putString("type", "Found");
		else if(donation.isChecked())
			filters.putString("type", "Donation");
		
		if(heirloom.isChecked())
			filters.putString("category", "Heirloom");
		else if(keepsake.isChecked())
			filters.putString("category", "Keepsake");
		else if(misc.isChecked())
			filters.putString("category", "Miscellaneous");
		
		if(date.isChecked())
		{
			filters.putSerializable("date", new Date(startDate.getYear()-1900,
					startDate.getMonth(), startDate.getDayOfMonth()));
			System.out.println("Date is checked: " + filters);
		}

		cancel();
	}
}
