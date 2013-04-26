package com.sherpas.wheresmystuff.support;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GpsManager implements LocationListener
{
	private static GpsManager gps;
	private static Context bc;
	private Context baseContext;
	private LocationManager manager;
	private Location currentLocation;
	private boolean isRunning = false;
	
	public static GpsManager getInstance()
	{
		if(gps == null)
		{
			synchronized(GpsManager.class)
			{
				if(gps == null)
				{
					gps = new GpsManager(bc);
				}
			}
		}
		return gps;
	}
	
	private GpsManager(Context b)
	{
		System.out.println("GPS created");
		baseContext = b;
		manager = (LocationManager)
				baseContext.getSystemService(Context.LOCATION_SERVICE);
		currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(currentLocation == null)
		{
			currentLocation = new Location(LocationManager.GPS_PROVIDER);
			currentLocation.setLatitude(33.777121);
			currentLocation.setLongitude(-84.396053);
		}
		startRunning();
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public void startRunning()
	{
		if(!isRunning)
		{
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600, 5, this);
			isRunning = true;
		}
	}
	
	public Location getCurrentLocation()
	{
		System.out.println("Current location is: " + currentLocation);
		return currentLocation;
	}
	
	public static void setBaseContext(Context c)
	{
		bc = c;
	}

	@Override
	public void onLocationChanged(Location l1)
	{
		currentLocation = l1;
		manager.removeUpdates(this);
		isRunning = false;
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		
	}

	@Override
	public void onProviderEnabled(String provider)
	{
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		
	}
}
