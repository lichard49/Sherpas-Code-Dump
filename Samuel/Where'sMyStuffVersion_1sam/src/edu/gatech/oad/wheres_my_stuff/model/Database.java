package edu.gatech.oad.wheres_my_stuff.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import android.util.Log;



public class Database {
	
	private static Database db;
	
	private static final String host = "http://www.samuelpclarke.com/sherpas/";

	protected Database()
	{
		
	}
	
	public static Database getInstance()
	{
		if(db == null)
		{
			synchronized(Database.class)
			{
				if(db==null)
					db = new Database();
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
	
	public Person authenticate(String email, String password)
	{
		String file = "authenticate.php";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONArray result = makeHttpRequest(host+file, "GET", params);
		if(result.length()!=0)
		{
			try {
				JSONObject jObject = result.getJSONObject(0);
				String firstName = jObject.getString("FirstName");
				String lastName = jObject.getString("LastName");
				String uEmail = jObject.getString("Email");
				long id = jObject.getLong("ID");
				loggedIn = new Person(firstName, lastName, uEmail, id);
				return loggedIn;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Person createUser()
	{
		return null;
	}
	
	public JSONArray makeHttpRequest(String url, String method, List<NameValuePair> params)
	{ 
		InputStream is = null;
		String json = null;
        // Making HTTP request 
        try { 
  
            // check for request method 
            if(method.equalsIgnoreCase("POST")){ 
                // request method is POST 
                // defaultHttpClient 
                DefaultHttpClient httpClient = new DefaultHttpClient(); 
                HttpPost httpPost = new HttpPost(url); 
                httpPost.setEntity(new UrlEncodedFormEntity(params)); 
  
                HttpResponse httpResponse = httpClient.execute(httpPost); 
                HttpEntity httpEntity = httpResponse.getEntity(); 
                is = httpEntity.getContent(); 
  
            }else if(method.equalsIgnoreCase("GET")){ 
                // request method is GET 
                DefaultHttpClient httpClient = new DefaultHttpClient(); 
                String paramString = URLEncodedUtils.format(params, "utf-8"); 
                url += "?" + paramString; 
                HttpGet httpGet = new HttpGet(url); 
  
                HttpResponse httpResponse = httpClient.execute(httpGet); 
                HttpEntity httpEntity = httpResponse.getEntity(); 
                is = httpEntity.getContent(); 
            }            
  
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();
            return null;
        } catch (ClientProtocolException e) { 
            e.printStackTrace(); 
            return null;
        } catch (IOException e) { 
            e.printStackTrace();
            return null;
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
            return null;
        } 
  
        System.out.println(json);
        
        // try parse the string to a JSON object 
        try { 
           JSONArray jArray = new JSONArray(json); 
           return jArray;
        } catch (JSONException e) { 
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            return null;
        } 
    }
	
	public static Person loggedIn;
}