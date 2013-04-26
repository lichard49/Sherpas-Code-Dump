package com.sherpas.wheresmystuff.presenter;

import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.view.IHomeView;

public class HomePresenter 
{
	private final IHomeView view;
	@SuppressWarnings("unused")
	private final IDatabaseModel model;
	
	/**
	 * Gets roles for the view and model
	 * 
	 * @param v view
	 * @param m model
	 */
	public HomePresenter(IHomeView v, IDatabaseModel m)
	{
		view = v;
		model = m;
	}
	
	public void onAddItemClick()
	{
		view.toAddItemActivity();
	}
	
	public void onFavoritesClick()
	{
		view.toFavoritesActivity();
	}
	
	public void onSearchClick()
	{
		view.toSearchActivity();
	}
	
	public void onMailClick()
	{
		view.toMailActivity();
	}
	
	public void onAdminClick()
	{
		view.toAdminActivity();
	}
	
	public void onSignoutClick()
	{
		view.toLoginActivity();
	}
}
