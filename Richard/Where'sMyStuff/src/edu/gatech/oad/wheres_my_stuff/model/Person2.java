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
public class Person2 {
		
	/** the persons firstName */
	private String firstName;
	/** the persons id */
	private long id;
	
	/** the persons lastName */
	private String lastName;
	/** the persons email */
	private String email;
	
	private String password;
	private String phone;
	private boolean isAdmin = false;
	
	public Person2(String fn, String ln, String e)
	{
		firstName = fn;
		lastName = ln;
		email = e;
	}
	
	public Person2(String fn, String ln, String e, String pw, String phone) {
		this(fn, ln, e);
		password = pw;
		this.phone = phone;
	}
	
	public Person2(String fn, String ln, String e, String pw, String phone, long id)
	{
		this(fn, ln, e, pw, phone);
		this.id = id;
	}
	
	public Person2(String fn, String ln, String e, String phone, long id)
	{
		this(fn, ln, e);
		this.id = id;
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
				attributes.getString(EMAIL, ""));
	}
}
