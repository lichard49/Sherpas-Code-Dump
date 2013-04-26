package com.sherpas.wheresmystuff.command;

import com.sherpas.wheresmystuff.model.IDatabaseModel;
import com.sherpas.wheresmystuff.model.Person;

public class AddUserCommand implements ICommand
{
	private String firstName, lastName, email, 
		phone, password;
	private boolean isAdmin;
	
	private IDatabaseModel db;
	
	private Person addedUser;
	
	public AddUserCommand(String firstName, String lastName, String email,
			String phone, String password, boolean isAdmin, IDatabaseModel d)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.isAdmin = isAdmin;
		
		db = d;
	}
	
	@Override
	public boolean execute()
	{
		addedUser = db.createUser(firstName, lastName, email, phone,
				password, false);
		return addedUser == null;
	}

	@Override
	public boolean undo()
	{
		if(getAddedUser() == null) return false;
		return db.deleteUser(getAddedUser().getEmail());
	}
	
	public Person getAddedUser()
	{
		return addedUser;
	}
}
