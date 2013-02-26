package com.example.premlogin;

import java.util.HashMap;
import java.util.Map;

public class Database {

	static  Map<String, String> map;
	static {
	    map = new HashMap<String, String>();
	    String[][] pairs = {
	        {"a","t"},{"Prem", "Saravanan"},{"Richard", "Li"},{"Samuel","Clarke"} };
	    for (String[] pair : pairs) {
	        map.put(pair[0], pair[1]);
	    }
	}
}