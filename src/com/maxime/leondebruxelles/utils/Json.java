package com.maxime.leondebruxelles.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.res.AssetManager;

/**
 * Télécharge et parse des fichiers Json.
 * @author Maxime Gens
 *
 */
public class Json {
	
	/**
	 * Télécharge un fichier json.
	 * @param url L'URL du Json à télécharger.
	 * @return String Le json en string.
	 */
	public static String getJsonFromURL(String url){
		
		InputStream is = null;
		String json = "";
		
		try {	    	
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch(Exception e) {
			return null;
		}
	    
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
	
	/**
	 * Charge un fichier json depuis les Assets de l'application.
	 * @param assetManager Le AssetManager de l'application.
	 * @param nameFile Le nom du ficher à charger.
	 * @return String Le json chargé en string.
	 */
	public static String getJsonFromAssets(AssetManager assetManager,String nameFile){
		String json = "";
		
		try {
	        InputStream is = assetManager.open(nameFile);
	        
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");

	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
}