package com.maxime.leondebruxelles.asynctask;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.beans.Restaurants;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.DownloadJson;
 
public class HttpListLeonTask extends AsyncTask<String, String, String> {

	private Context context;
	private TextView t;
	
	public HttpListLeonTask(Context c){
		this.context = c;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	
	@Override
	protected String doInBackground(String... params) {
		return DownloadJson.getJsonFromURL(Constantes.URL_LEON_DE_BRUXELLES);
	}
	
	@Override
	protected void onPostExecute(String result) {
		StringBuilder str = new StringBuilder();
		
		Restaurants restaurants = new Gson().fromJson(result, Restaurants.class);
		
		System.out.println("---------------------- Les Restaurants ------------------ ");
		for (LeonDeBruxelles leon : restaurants.restaurants) {
			str.append(leon.getId()+" - "+leon.getNom()+"\n");
			System.out.println(leon.getId()+" - "+leon.getNom());
		}
		System.out.println("---------------------------------------- ");
	}
}