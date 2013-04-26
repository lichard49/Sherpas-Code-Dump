package com.sherpas.wheresmystuff.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

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
	DBItem addItem(String name, String description, int typeID, int categoryID, boolean isResolved, long posterID, Date datePosted);
	DBItem addItem(String name, String description, int typeID, int categoryID, boolean isResolved, double lat, double lon, long posterID, Date datePosted);
	ArrayList<DBItem> getAllItems();
	Map<String, Integer> getCategoryTable();
	Map<String, Integer> getTypeTable();
	ArrayList<DBItem> getItemsByTypeID(int typeID);
	ArrayList<DBItem> getItemsByCategoryID(int categoryID);
	ArrayList<DBItem> getItemsPostedAfterDate(Date date);
	ArrayList<DBItem> filterItems(int typeID, int categoryID, Date date, String name, String description, String city, String state);
	boolean createImage(long itemID, int ordinal, Bitmap b);
	ArrayList<DBImage> getImages(long itemID);
}
