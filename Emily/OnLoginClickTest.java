package com.sherpas.wheresmystuff.test;

import junit.framework.TestCase;

import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.presenter.LoginPresenter;

public class OnLoginClickTest extends TestCase {
	public void test(){
		LoginPresenter presenter=new LoginPresenter(new MockOnLoginClick(), DatabaseModel.getInstance());
		
		Person temp = presenter.onLoginClick(name, password);
		
		assertEquals(name, temp.getEmail());
		assertEquals(password, temp.getPassword());
		}
}
