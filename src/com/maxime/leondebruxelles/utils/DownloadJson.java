package com.maxime.leondebruxelles.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Retreive a Json file and parse this file. 
 * @author Maxime Gens
 *
 */
public class DownloadJson {
	
	/**
	 * Retreive a Json file.
	 * @param url URl link to download
	 * @return a json string
	 */
	public static String getJsonFromURL(String url){
		
		InputStream is = null;
		String json = "";
		
		// Call HTTP
		try {	    	
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch(Exception e) {
			return null;
		}
	    
		// Read response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			System.out.println(is);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			json = sb.toString();
			return json;
		} catch(Exception e) {
			return null;
		}
	}
}