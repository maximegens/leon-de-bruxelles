package com.maxime.leondebruxelles.asynctask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.JsonParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
 
public class HttpListLeonTask extends AsyncTask<String, String, JSONObject> {

	private Context context;
	private TextView t;
	
	public HttpListLeonTask(Context c, TextView t){
		this.context = c;
		this.t = t;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	
	@Override
	protected JSONObject doInBackground(String... params) {
		return JsonParser.getJsonFromURL(Constantes.URL_LEON_DE_BRUXELLES);
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		System.out.println("-------------------------------");
		t.setText(result.toString());
		System.out.println("-------------------------------");
	}
	
}