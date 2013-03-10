package edu.gatech.oad.wheres_my_stuff.model;


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
	/** the persons id */
	private long id;
	
	/** the persons lastName */
	private String lastName;
	/** the persons email */
	private String email;
	
	private String password;
	private String phone;
	
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
}
