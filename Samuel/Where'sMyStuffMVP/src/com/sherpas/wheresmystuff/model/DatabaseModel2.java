package com.sherpas.wheresmystuff.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;



public class DatabaseModel2 implements IDatabaseModel
{
	private static final long timeOut = 5;
	private static final TimeUnit timeOutUnit = TimeUnit.SECONDS;
	private static DatabaseModel db;
	
	private static final String host = "http://www.samuelpclarke.com/sherpas/";

	protected DatabaseModel2()
	{
		
	}
	
	public static DatabaseModel getInstance()
	{
		if(db == null)
		{
			synchronized(DatabaseModel.class)
			{
				if(db==null)
					db = new DatabaseModel();
			}
		}
		return db;
	}
	
	public boolean emailExists(String email)
	{
		String file = "emailExists.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		try {
			return result.getJSONObject(0).getInt("exist") == 1;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean emailLocked(String email)
	{
		String file = "emailLocked.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		try {
			return result.getJSONObject(0).getInt("Locked") == 1;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Person authenticate(String email, String password)
	{
		String file = "authenticate.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		if(result!=null&&result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				String firstName = jObject.getString("FirstName");
				String lastName = jObject.getString("LastName");
				String uEmail = jObject.getString("Email");
				String phone = jObject.getString("Phone");
				long id = jObject.getLong("ID");
				boolean locked = (jObject.getInt("Locked")==1);
				boolean isAdmin = (jObject.getInt("IsAdmin")==1);
				loggedIn = new Person(firstName, lastName, uEmail, phone, id, locked, isAdmin);
				return loggedIn;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Person createUser(String firstName, String lastName, String email, String phone, String password, boolean isAdmin)
	{
		String file = "createUser.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firstName", firstName));
		params.add(new BasicNameValuePair("lastName", lastName));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("isAdmin", (isAdmin?"1":"0")));
		JSONArray result = makeHttpRequest(host+file, "POST", params);
		if(result!=null && result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				int success = jObject.getInt("success");
				if(success==1)
				{
					long id = jObject.getLong("ID");
					loggedIn = new Person(firstName, lastName, email, phone, id);
					return loggedIn;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean setAdmin(String email, boolean isAdmin)
	{
		String file = "updateAdmin.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("isAdmin", (isAdmin?"1":"0")));
		JSONArray result = makeHttpRequest(host+file, "POST", params);
		if(result!=null && result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				int success = jObject.getInt("success");
				return success==1;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean setLocked(String email, boolean isLocked)
	{
		String file = "updateLock.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("locked", (isLocked?"1":"0")));
		JSONArray result = makeHttpRequest(host+file, "POST", params);
		if(result!=null && result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				int success = jObject.getInt("success");
				return success==1;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//public MyItem createItem()
	
	public Person getPerson(long ID)
	{
		String file = "getPersonByID.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ID", ID+""));
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		if(result!=null&&result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				String firstName = jObject.getString("FirstName");
				String lastName = jObject.getString("LastName");
				String uEmail = jObject.getString("Email");
				String phone = jObject.getString("Phone");
				long id = jObject.getLong("ID");
				loggedIn = new Person(firstName, lastName, uEmail, phone, id);
				return loggedIn;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Person> getLocked()
	{
		String file = "getLocked.php";
		ArrayList<Person> lockedList = new ArrayList<Person>();
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		if(result!=null&&result.length()!=0)
		{
			try {
				for(int i = 0; i<result.length(); i++)
				{
					JSONObject jObject = result.getJSONObject(i);
					String firstName = jObject.getString("FirstName");
					String lastName = jObject.getString("LastName");
					String uEmail = jObject.getString("Email");
					String phone = jObject.getString("Phone");
					long id = jObject.getLong("ID");
					lockedList.add(new Person(firstName, lastName, uEmail, phone, id));
				}
				return lockedList;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Person> getAllUsers()
	{
		String file = "getAllUsers.php";
		ArrayList<Person> lockedList = new ArrayList<Person>();
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		if(result!=null&&result.length()!=0)
		{
			for(int i = 0; i<result.length(); i++)
			{
				String firstName = null, lastName = null,
						uEmail = null, phone = null;
				long id = -1;
				boolean locked = false, admin = false;
				try
				{	
					JSONObject jObject = result.getJSONObject(i);
					firstName = jObject.getString("FirstName");
					lastName = jObject.getString("LastName");
					uEmail = jObject.getString("Email");
					phone = jObject.getString("Phone");
					id = jObject.getLong("ID");
					locked = jObject.getInt("Locked") == 0;
					admin = jObject.getInt("IsAdmin") == 0;
					
					return lockedList;
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				finally
				{
					lockedList.add(new Person(firstName, lastName,
							uEmail, phone, id, locked, admin));
				}
			}
		}
			
		return lockedList;
	}
	
	public boolean deleteUser(long ID)
	{
		String file = "deleteUserByID.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ID", ID+""));
		JSONArray result = makeHttpRequest(host+file, "POST", params);
		if(result!=null && result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				int success = jObject.getInt("success");
				return success==1;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean deleteUser(String email)
	{
		String file = "deleteUserByEmail.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		JSONArray result = makeHttpRequest(host+file, "POST", params);
		if(result!=null && result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				int success = jObject.getInt("success");
				return success==1;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
//	public JSONArray makeHttpRequest(String url, String method, List<NameValuePair> params)
//	{ 
//		InputStream is = null;
//		String json = null;
//        // Making HTTP request 
//        try { 
//  
//            // check for request method 
//            if(method.equalsIgnoreCase("POST")){ 
//                // request method is POST 
//                // defaultHttpClient 
//                DefaultHttpClient httpClient = new DefaultHttpClient(); 
//                HttpPost httpPost = new HttpPost(url); 
//                httpPost.setEntity(new UrlEncodedFormEntity(params)); 
//  
//                HttpResponse httpResponse = httpClient.execute(httpPost); 
//                HttpEntity httpEntity = httpResponse.getEntity(); 
//                is = httpEntity.getContent(); 
//  
//            }else if(method.equalsIgnoreCase("GET")){ 
//                // request method is GET 
//                DefaultHttpClient httpClient = new DefaultHttpClient(); 
//                String paramString = URLEncodedUtils.format(params, "utf-8"); 
//                url += "?" + paramString; 
//                HttpGet httpGet = new HttpGet(url); 
//  
//                HttpResponse httpResponse = httpClient.execute(httpGet); 
//                HttpEntity httpEntity = httpResponse.getEntity(); 
//                is = httpEntity.getContent(); 
//            }            
//  
//        } catch (UnsupportedEncodingException e) { 
//            e.printStackTrace();
//            return null;
//        } catch (ClientProtocolException e) { 
//            e.printStackTrace(); 
//            return null;
//        } catch (IOException e) { 
//            e.printStackTrace();
//            return null;
//        } 
//  
//        try { 
//            BufferedReader reader = new BufferedReader(new InputStreamReader( 
//                    is, "iso-8859-1"), 8); 
//            StringBuilder sb = new StringBuilder(); 
//            String line = null; 
//            while ((line = reader.readLine()) != null) { 
//                sb.append(line + "\n");
//            } 
//            is.close(); 
//            json = sb.toString(); 
//        } catch (Exception e) { 
//            Log.e("Buffer Error", "Error converting result " + e.toString()); 
//            return null;
//        } 
//  
//        System.out.println(json);
//        
//        // try parse the string to a JSON object 
//        try { 
//           JSONArray jArray = new JSONArray(json); 
//           return jArray;
//        } catch (JSONException e) { 
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//            return null;
//        } 
//    }
	
	private JSONArray makeHttpRequest(String url, String method, List<NameValuePair> params)
	{
		HTTPRequest request = new HTTPRequest(url,method,params);
		HTTPTask task= new HTTPTask();
		task.execute(request);
		List<JSONArray> result;
		try {
			result = task.get(DatabaseModel2.timeOut, DatabaseModel2.timeOutUnit);
			return result.get(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Person loggedIn;
	
	private class HTTPRequest
	{
		String url;
		String method;
		List<NameValuePair> params;
		
		public HTTPRequest(String url, String method, List<NameValuePair> params)
		{
			this.url = url;
			this.method = method;
			this.params = params;
		}
	}
	
	private class HTTPTask extends AsyncTask<HTTPRequest, Integer, List<JSONArray>>
	{

		@Override
		protected List<JSONArray> doInBackground(HTTPRequest... params) 
		{
			ArrayList<JSONArray> resultList = new ArrayList<JSONArray>();
			for(int i = 0; i<params.length; i++)
			{
				InputStream is = null;
				String json = null;
		        // Making HTTP request 
		        try { 
		  
		            // check for request method 
		            if(params[i].method.equalsIgnoreCase("POST")){ 
		                // request method is POST 
		                // defaultHttpClient 
		                DefaultHttpClient httpClient = new DefaultHttpClient(); 
		                HttpPost httpPost = new HttpPost(params[i].url); 
		                httpPost.setEntity(new UrlEncodedFormEntity(params[i].params)); 
		  
		                HttpResponse httpResponse = httpClient.execute(httpPost); 
		                HttpEntity httpEntity = httpResponse.getEntity(); 
		                is = httpEntity.getContent(); 
		  
		            }else if(params[i].method.equalsIgnoreCase("GET")){ 
		                // request method is GET 
		                DefaultHttpClient httpClient = new DefaultHttpClient(); 
		                String paramString = URLEncodedUtils.format(params[i].params, "utf-8"); 
		                params[i].url += "?" + paramString; 
		                HttpGet httpGet = new HttpGet(params[i].url); 
		  
		                HttpResponse httpResponse = httpClient.execute(httpGet); 
		                HttpEntity httpEntity = httpResponse.getEntity(); 
		                is = httpEntity.getContent(); 
		            }            
		  
		        } catch (UnsupportedEncodingException e) { 
		            e.printStackTrace();
		            resultList.add(null);
		            continue;
		        } catch (ClientProtocolException e) { 
		            e.printStackTrace(); 
		            resultList.add(null);
		            continue;
		        } catch (IOException e) { 
		            e.printStackTrace();
		            resultList.add(null);
		            continue;
		        } 
		  
		        try { 
		            BufferedReader reader = new BufferedReader(new InputStreamReader( 
		                    is, "iso-8859-1"), 8); 
		            StringBuilder sb = new StringBuilder(); 
		            String line = null; 
		            while ((line = reader.readLine()) != null) { 
		                sb.append(line + "\n");
		            } 
		            is.close(); 
		            json = sb.toString(); 
		        } catch (Exception e) { 
		            Log.e("Buffer Error", "Error converting result " + e.toString()); 
		            resultList.add(null);
		            continue;
		        } 

		        System.out.println(json);
		        
		        // try parse the string to a JSON object 
		        try { 
		           JSONArray jArray = new JSONArray(json); 
		           resultList.add(jArray);
		        } catch (JSONException e) { 
		            Log.e("JSON Parser", "Error parsing data " + e.toString());
		            resultList.add(null);
		            continue;
		        }
			}
			return resultList;
		}
	}

	public void addItem(Item i) {
		// TODO Auto-generated method stub
		
	}

	public Item getItem(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Item[] getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBItem addItem(String name, String description, int typeID,
			int categoryID, boolean isResolved, long posterID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DBItem> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getTypeTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getCategoryTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DBItem> getItemsByTypeID(int typeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DBItem> getItemsByCategoryID(int categoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DBItem> getItemsPostedAfterDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<DBItem> filterItems(int typeID, int categoryID, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBItem addItem(String name, String description, int typeID,
			int categoryID, boolean isResolved, long posterID, Date datePosted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createImage(long itemID, int ordinal, Bitmap b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<DBImage> getImages(long itemID) {
		// TODO Auto-generated method stub
		return null;
	}
}