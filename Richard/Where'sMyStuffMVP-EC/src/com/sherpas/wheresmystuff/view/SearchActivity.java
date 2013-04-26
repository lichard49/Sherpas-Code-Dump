package com.sherpas.wheresmystuff.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sherpas.wheresmystuff.R;
import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.presenter.SearchPresenter;
import com.sherpas.wheresmystuff.support.FilterOptionDialog;

/**
 * Search activity that lists all the items by a given criteria
 * 
 * @author Richard
 *
 */
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
		presenter = new SearchPresenter(this, DatabaseModel.getInstance(), filters);
		
		list = (ListView) findViewById(R.id.list_of_items);
		list.setAdapter(presenter.populateItemAdapter(this));
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int pos,
					long id) 
			{
				presenter.onItemClick(pos);
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
		
		final EditText searchBox = (EditText) findViewById(R.id.keyword_field);
		searchBox.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable e)
			{
				String query = searchBox.getText().toString();
				presenter.search(query);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) { }

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) { }
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
	
	public void toViewItem(String name)
	{
		Intent toViewItemActivity = new Intent(this, ViewItemActivity.class);
		toViewItemActivity.putExtra("itemName", name);
		startActivity(toViewItemActivity);
	}
	
	public void restart()
	{
		list.setAdapter(presenter.populateItemAdapter(this));
	}
}
