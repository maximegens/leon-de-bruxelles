package com.maxime.leondebruxelles.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.adapter.ListLeonAdapter;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.beans.Restaurants;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.DownloadJson;

public class ListLeonBruxellesFragment extends Fragment{
	OnLeonSelectedListener mCallback;
	
    ArrayList<LeonDeBruxelles> lesLeons;  
	Restaurants restaurants;
    HttpListLeonTask listLeon;
    ListLeonAdapter adapterListLeon;
    ListView listViewLeon;

    public interface OnLeonSelectedListener {
        public void onLeonSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lesLeons = new ArrayList<LeonDeBruxelles>();  
    	restaurants = null;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	View myInflatedView = inflater.inflate(R.layout.list_leon_view, container,false);

    	listViewLeon = (ListView)myInflatedView.findViewById(R.id.list_view_leon);
        listLeon = new HttpListLeonTask();    
        listLeon.execute();
        
        listViewLeon.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView id_text = (TextView)view.findViewById(R.id.adapter_leon_id);
				String id_string = id_text.getText().toString();
					mCallback.onLeonSelected(Integer.parseInt(id_string));
			}
		});
        
        return myInflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.detail_fragment) != null) {
            listViewLeon.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnLeonSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        mCallback.onLeonSelected(position);
//        getListView().setItemChecked(position, true);
//    } 
    
    private class HttpListLeonTask extends AsyncTask<String, String, Restaurants> {
	
    	
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
    		for (LeonDeBruxelles leon : restaurants.restaurants) {
    			lesLeons.add(leon);
    		}
    		adapterListLeon = new ListLeonAdapter(getActivity(), lesLeons);
    		listViewLeon.setAdapter(adapterListLeon);
    		
    	}
    }
}