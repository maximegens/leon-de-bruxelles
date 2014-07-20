package com.maxime.leondebruxelles.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.beans.Restaurants;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.DownloadJson;
 
public class HttpListLeonTask extends AsyncTask<String, String, Restaurants> {

	private Context context;
	
	public HttpListLeonTask(Context c){
		this.context = c;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected Restaurants doInBackground(String... params) {
		String json = DownloadJson.getJsonFromURL(Constantes.URL_LEON_DE_BRUXELLES);
		Restaurants restaurants = new Gson().fromJson(json, Restaurants.class);
		return restaurants;
	}
	
	@Override
	protected void onPostExecute(Restaurants restaurants) {

		
	}
}