package com.sherpas.wheresmystuff.model;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Represents a single Person in the model
 * Information Holder 
 * 
 * @author Richard
 *
 */
public class Person {
	
	public static String ROOT_ADMIN = "admin";
	
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
	private boolean isLocked;
	private boolean isAdmin;
	
	public Person(String fn, String ln, String e)
	{
		firstName = fn;
		lastName = ln;
		email = e;
	}
	
	public Person(String fn, String ln, String e, String pw, String phone) {
		this(fn, ln, e);
		password = pw;
		this.phone = phone;
	}
	
	public Person(String fn, String ln, String e, String pw, String phone, long id)
	{
		this(fn, ln, e, pw, phone);
		this.id = id;
	}
	
	public Person(String fn, String ln, String e, String phone, long id)
	{
		this(fn, ln, e);
		this.id = id;
		this.phone = phone;
	}
	
	public Person(String fn, String ln, String e, String phone, long id, boolean isLocked, boolean isAdmin)
	{
		this(fn, ln, e, phone, id);
		this.isLocked = isLocked;
		this.isAdmin = isAdmin;
	}
	
	public Person(String fn, String ln, String e, String password, String phone, long id, boolean isLocked, boolean isAdmin)
	{
		this(fn, ln, e, phone, id, isLocked, isAdmin);
		this.password=password;
	}
	
	public Person(String fn, String ln, String e, String password, String phone, long id, boolean isAdmin)
	{
		this(fn, ln, e, password, phone, id);
		this.isAdmin = isAdmin;
	}
	
	/**
	 * 
	 * @return the persons name
	 */
	public String getFirstName() { return firstName; }
	/**
	 * 
	 * @return the persons name
	 */
	public String getLastName() { return lastName; }
	/**
	 * 
	 * @return the persons id
	 */
	/*public String getUid() { return uid; }
	*//**
	 * 
	 * @return the persons email
	 */
	
	public String getEmail() { return email; }

	public String getPassword() { return password; }
	
	public boolean isLocked()
	{
		return isLocked;
	}
	
	public boolean isAdmin()
	{
		return isAdmin;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public long getID()
	{
		return id;
	}
	
	private static final String LOGGED_IN_USER = "LoggedInUser";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String PHONE = "Phone";
    private static final String ADMIN = "Admin";
    private static final String ID = "ID";
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
        editor.putString(PHONE, p.getPhone());
        editor.putString(ID, p.getID()+"");
        editor.commit();
    }

    public static void logoutUser(Context c)
    {
    	setLoggedInUser(new Person("", "", "", "", "", 0, false), c);
    }
    
    public static Person getLoggedInUser(Context c)
    {
        SharedPreferences attributes = c.getSharedPreferences(
                LOGGED_IN_USER, Context.MODE_PRIVATE);
        return new Person(attributes.getString(FIRST_NAME, ""),
                attributes.getString(LAST_NAME, ""),
                attributes.getString(EMAIL, ""),                
                attributes.getString(PASSWORD, ""),
                attributes.getString(PHONE, ""),
                Long.parseLong(attributes.getString(ID,"")),
                attributes.getBoolean(ADMIN, false));
    }
}
