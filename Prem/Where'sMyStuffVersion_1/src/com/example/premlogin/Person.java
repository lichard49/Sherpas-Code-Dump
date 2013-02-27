package com.example.premlogin;


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
	
	/**
	 * Makes a new person
	 * @param n the name
	 * @param u the id
	 * @param e the email
	 */
	public Person(String fn, String ln, String e) {
		firstName = fn;
		lastName = ln;
		email = e;
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

}
