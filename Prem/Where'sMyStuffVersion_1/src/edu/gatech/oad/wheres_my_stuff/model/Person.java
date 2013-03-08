package edu.gatech.oad.wheres_my_stuff.model;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Represents a single Person in the model
 * Information Holder 
 * 
 * @author Robert
 *
 */
public class Person {
	/** the persons firstName */
	private String firstName;
	/** the persons id *//*
	private String uid;*/
	
	/** the persons lastName */
	private String lastName;
	/** the persons email */
	private String email;
	
	private String password;
	private boolean isAdmin;
	
	/**
	 * Makes a new person
	 * @param n the name
	 * @param u the id
	 * @param e the email
	 */
	public Person(String fn, String ln, String e, String pw, boolean ia) {
		firstName = fn;
		lastName = ln;
		email = e;
		password = pw;
		isAdmin = ia;
	}
	
	/**
	 * 
	 * @return the persons name
	 */
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public boolean isAdmin() { return isAdmin; }
	
	private static final String LOGGED_IN_USER = "LoggedInUser";
	private static final String FIRST_NAME = "FirstName";
	private static final String LAST_NAME = "LastName";
	private static final String EMAIL = "Email";
	private static final String PASSWORD = "Password";
	private static final String ADMIN = "Admin";
	public static void setLoggedInUser(Person p, Context c)
	{
		SharedPreferences attributes = c.getSharedPreferences(
				LOGGED_IN_USER, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = attributes.edit();
		editor.putString(FIRST_NAME, p.getFirstName());
		editor.putString(LAST_NAME, p.getLastName());
		editor.putString(EMAIL, p.getEmail());
		editor.putString(PASSWORD, p.getPassword());
		editor.putBoolean(ADMIN, p.isAdmin());
		editor.commit();
	}
	
	public static Person getLoggedInUser(Context c)
	{
		SharedPreferences attributes = c.getSharedPreferences(
				LOGGED_IN_USER, Context.MODE_PRIVATE);
		return new Person(attributes.getString(FIRST_NAME, ""),
				attributes.getString(LAST_NAME, ""),
				attributes.getString(EMAIL, ""),
				attributes.getString(PASSWORD, "")
				attributes.getBoolean(ADMIN, false);
	}
}
