package com.sherpas.wheresmystuff.test;

import java.util.List;

import com.sherpas.wheresmystuff.model.DatabaseModel;
import com.sherpas.wheresmystuff.model.Person;
import com.sherpas.wheresmystuff.presenter.AdminPresenter;

import junit.framework.TestCase;

public class OnMakeAdminTest extends TestCase {

	public void testOnMakeAdminClick()
	{
		String emailCheck = "sam@sam.net";
		DatabaseModel d = DatabaseModel.getInstance();
		assertTrue(d.setAdmin(emailCheck, false));
		AdminPresenter ap = new AdminPresenter(new MockAdminActivity(), DatabaseModel.getInstance());
		Person loggedIn = new Person("test", "test", "test", "123", "test", 1, false, true);
		ap.onMakeAdminClick(emailCheck, loggedIn);
		List<Person> allPeople = d.getAllUsers();
		boolean found = false;
		for(Person p: allPeople)
		{
			if(p.getEmail().equals(emailCheck))
			{
				assertTrue(p.isAdmin());
				found = true;
			}
		}
		assertTrue(found);
	}

}
