package com.sherpas.wheresmystuff.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempDatabaseModel implements IDatabaseModel 
{
	private static TempDatabaseModel db = null;
	private static Map<String, DBItem> itemMap;
	private static Map<String, Integer> typeTable;
	private static Map<String, Integer> categoryTable;
	
	protected TempDatabaseModel()
	{
		itemMap = new HashMap<String, DBItem>();
		typeTable = new HashMap<String, Integer>();
		typeTable.put("Keepsake", 0);
		typeTable.put("Heirloom", 1);
		typeTable.put("Miscellaneous", 2);
		categoryTable = new HashMap<String, Integer>();
		categoryTable.put("Lost", 0);
		categoryTable.put("Found", 1);
		categoryTable.put("Donation", 2);
	}
	
	public static TempDatabaseModel getInstance()
	{
		if(db == null)
		{
			synchronized(TempDatabaseModel.class)
			{
				if(db==null)
					db = new TempDatabaseModel();
			}
		}
		return db;
	}

	@Override
	public boolean emailExists(String email) {
		return false;
	}

	@Override
	public boolean emailLocked(String email) {
		return false;
	}

	@Override
	public Person authenticate(String email, String password) {
		return null;
	}

	@Override
	public Person createUser(String firstName, String lastName, String email,
			String phone, String password, boolean isAdmin) {
		return null;
	}

	@Override
	public boolean setAdmin(String email, boolean isAdmin) {
		return false;
	}

	@Override
	public boolean setLocked(String email, boolean isLocked) {
		return false;
	}

	@Override
	public Person getPerson(long ID) {
		return null;
	}

	@Override
	public List<Person> getLocked() {
		return null;
	}

	@Override
	public List<Person> getAllUsers() {
		return null;
	}

	@Override
	public boolean deleteUser(long ID) {
		return false;
	}

	@Override
	public boolean deleteUser(String email) {
		return false;
	}

	@Override
	public DBItem addItem(String name, String description, int typeID,
			int categoryID, boolean isResolved, long posterID)
	{
		DBItem item = new DBItem(itemMap.size(), name, description,
				typeID, "", categoryID, "", isResolved, posterID, null);
		itemMap.put(name, item);
		return item;
	}

	@Override
	public DBItem addItem(String name, String description, int typeID,
			int categoryID, boolean isResolved, Date date, long posterID)
	{
		DBItem item = new DBItem(itemMap.size(), name, description,
				typeID, "", categoryID, "", isResolved, posterID, date);
		itemMap.put(name, item);
		return item;
	}
	
	@Override
	public ArrayList<DBItem> getAllItems()
	{
		return new ArrayList<DBItem>(itemMap.values());
	}

	
	@Override
	public Map<String, Integer> getTypeTable()
	{
		return typeTable;
	}

	@Override
	public Map<String, Integer> getCategoryTable()
	{
		return categoryTable;
	}

	@Override
	public ArrayList<DBItem> getItemsByTypeID(int typeID)
	{
		ArrayList<DBItem> result = new ArrayList<DBItem>();
		for(DBItem i: itemMap.values())
		{
			if(i.getTypeID() == typeID) result.add(i);
		}
		return result;
	}

	@Override
	public ArrayList<DBItem> getItemsByCategoryID(int categoryID)
	{
		ArrayList<DBItem> result = new ArrayList<DBItem>();
		for(DBItem i: itemMap.values())
		{
			if(i.getCategoryID() == categoryID) result.add(i);
		}
		return result;
	}

	@Override
	public ArrayList<DBItem> getItemsPostedAfterDate(Date date)
	{
		ArrayList<DBItem> result = new ArrayList<DBItem>();
		for(DBItem i: itemMap.values())
		{
			if(i.getDatePosted().after(date)) result.add(i);
		}
		return result;
	}	
}
