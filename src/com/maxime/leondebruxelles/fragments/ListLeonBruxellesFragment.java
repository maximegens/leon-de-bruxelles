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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.adapter.ListLeonAdapter;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.beans.Restaurants;
import com.maxime.leondebruxelles.utils.Connection;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.Json;

public class ListLeonBruxellesFragment extends Fragment{
	OnLeonSelectedListener mCallback;
	
    ArrayList<LeonDeBruxelles> lesLeons;  
	Restaurants restaurants;
	RetreiveListLeonTask listLeon;
    ListLeonAdapter adapterListLeon;
    ListView listViewLeon;
    TextView loaderText;
    ProgressBar loader;
    boolean isConnected;

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
    	loader = (ProgressBar)myInflatedView.findViewById(R.id.progress_bar_list_leon);
    	loaderText = (TextView)myInflatedView.findViewById(R.id.text_progress_bar_list_leon);
    	
    	// recupere la liste des leon de bruxelles via une Asynctask en lui passant en parametre un boolean indiquant si le device est online ou non
    	isConnected = Connection.isConnectedInternet(getActivity());
	    listLeon = new RetreiveListLeonTask();    
	    listLeon.execute(isConnected);

        listViewLeon.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					TextView id_text = (TextView)view.findViewById(R.id.adapter_leon_id);
					String id_string = id_text.getText().toString();
					mCallback.onLeonSelected(Integer.parseInt(id_string));
					listViewLeon.setItemChecked(position, true);
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

    /**
     * Récupére la liste des Leons de Bruxelles pour les afficher à l'utilisateur.
     * 
     */
    private class RetreiveListLeonTask extends AsyncTask<Boolean, String, Restaurants> {
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		loader.setVisibility(View.VISIBLE);
    		loaderText.setVisibility(View.VISIBLE);
    	}

    	@Override
    	protected Restaurants doInBackground(Boolean... params) {
    		
    		boolean isConnected = params[0];
    		String json;
    		
    		/* Si l'appareil est online on récupere la liste via le webservice sinon on charge le json local des Leons de Bruxelles*/
    		if(isConnected)
    			json = Json.getJsonFromURL(Constantes.URL_LEON_DE_BRUXELLES);
    		else
    			json = Json.getJsonFromAssets(getActivity().getAssets(),Constantes.LOCAL_LEON_DE_BRUXELLES); 
    		
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
    		loader.setVisibility(View.INVISIBLE);
    		loaderText.setVisibility(View.INVISIBLE);
    	}
    }
}