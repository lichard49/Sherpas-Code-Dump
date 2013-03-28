package com.sherpas.wheresmystuff.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Database's interaction with the outside world
 * 
 * @author Richard
 *
 */
public interface IDatabaseModel 
{
	// TODO: Perhaps this interface should be split up?
	boolean emailExists(String email);
	boolean emailLocked(String email);
	Person authenticate(String email, String password);
	Person createUser(String firstName, String lastName, String email, String phone, String password, boolean isAdmin);
	boolean setAdmin(String email, boolean isAdmin);
	boolean setLocked(String email, boolean isLocked);
	Person getPerson(long ID);
	List<Person> getLocked();
	List<Person> getAllUsers();
	boolean deleteUser(long ID);
	boolean deleteUser(String email);
	DBItem addItem(String name, String description, int typeID, int categoryID, boolean isResolved, long posterID);
	ArrayList<DBItem> getAllItems();
	DBItem addItem(String name, String description, int typeID, int categoryID, boolean isResolved, Date date, long posterID);
	Map<String, Integer> getTypeTable();
	Map<String, Integer> getCategoryTable();
	ArrayList<DBItem> getItemsByTypeID(int typeID);
	ArrayList<DBItem> getItemsByCategoryID(int categoryID);
	ArrayList<DBItem> getItemsPostedAfterDate(Date date);
	
}
