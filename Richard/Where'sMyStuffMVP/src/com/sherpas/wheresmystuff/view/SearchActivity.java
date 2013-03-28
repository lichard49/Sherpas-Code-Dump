package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.TempDatabaseModel;
import com.sherpas.wheresmystuff.presenter.SearchPresenter;
import com.sherpas.wheresmystuff.support.FilterOptionDialog;

public class SearchActivity extends Activity implements ISearchView
{
	private SearchPresenter presenter;
	private Bundle filters;
	private ListView list;
	private Dialog dialog;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		filters = new Bundle();
		dialog = new FilterOptionDialog(this, filters);
		presenter = new SearchPresenter(this, TempDatabaseModel.getInstance(), filters);
		
		list = (ListView) findViewById(R.id.list_of_items);
		list.setAdapter(presenter.populateItemAdapter(this));
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int pos,
					long id) 
			{
				
			}
		});
		
		final Button filterButton = (Button) findViewById(R.id.filter_button);
		filterButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				showDialog();
			}
		});
	}
	
	public void showDialog()
	{
		dialog.show();
		dialog.setOnCancelListener(new OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface d)
			{
				restart();
			}
		});
	}
	
	public void restart()
	{
		list.setAdapter(presenter.populateItemAdapter(this));
	}
}
