package com.maxime.leondebruxelles.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	
	public static JSONObject getJsonFromURL(String url){
		
		InputStream is = null;
		String result = "";
		JSONObject jsonObject = null;
		
		// HTTP
		try {	    	
			HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch(Exception e) {
			return null;
		}
	    
		// Read response to string
		try {
			int nb = 0 ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			System.out.println(is);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				nb += line.length();
				sb.append(line);
			}
			is.close();
			System.out.println("------ TAILLE ------");
			System.out.println("nb : "+nb);
			System.out.println("sb : "+sb.length());
			System.out.println("------------");
			result = sb.toString();	   
		} catch(Exception e) {
			return null;
		}
 
		// Convert string to object
		try {
			jsonObject = new JSONObject(result);            
		} catch(JSONException e) {
			System.err.println("Impossible de convertir en JSONObject");
		}
    
		return jsonObject;
 
	}
	
}

	
