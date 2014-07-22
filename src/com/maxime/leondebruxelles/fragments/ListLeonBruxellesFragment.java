package com.maxime.leondebruxelles.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.activity.PermissionGPSActivity;
import com.maxime.leondebruxelles.adapter.ListLeonAdapter;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.beans.Restaurants;
import com.maxime.leondebruxelles.utils.Connection;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.Json;

public class ListLeonBruxellesFragment extends Fragment implements LocationListener{
	
	OnLeonSelectedListener mCallback;
    ArrayList<LeonDeBruxelles> lesLeons;  
	Restaurants restaurants;
	RetreiveListLeonTask listLeon;
    ListLeonAdapter adapterListLeon;
    ListView listViewLeon;
    TextView loaderText;
    ProgressBar loader;
    boolean isConnected;
    LocationManager locationManager;
    

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
    	
    	// Recupere la liste des leon de bruxelles via une Asynctask en lui passant en parametre un boolean indiquant si le device est online ou non
    	// Si la liste n'a pas déjà été récupéré on la télécharge
    	
    	if(savedInstanceState == null){
	    	isConnected = Connection.isConnectedInternet(getActivity());
		    listLeon = new RetreiveListLeonTask();    
		    listLeon.execute(isConnected);
    	}else{
    		lesLeons = savedInstanceState.getParcelableArrayList("listLeonParcelable");
    		adapterListLeon = new ListLeonAdapter(getActivity(), lesLeons);
    		listViewLeon.setAdapter(adapterListLeon);
    	}
    	
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
	public void onResume() {
		super.onResume();
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && Constantes.CHOICE_TO_ACTIVATE_GPS){
			  Intent localIntent = new Intent(getActivity(), PermissionGPSActivity.class);
			  localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  startActivity(localIntent);
		}
			
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,this);
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0,this);
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	
	
    @Override
    public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
       outState.putParcelableArrayList("listLeonParcelable", lesLeons);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnLeonSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }
    }

    /**
     * Récupére la liste des Leons de Bruxelles pour les afficher à l'utilisateur.
     * 
     */
    private class RetreiveListLeonTask extends AsyncTask<Boolean, String, Restaurants> {
    	float[] distance = new float[3];
    	
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
    		
    		//Calcul de la distance vers le Leon et ajout de la liste a l'adapter
    		lesLeons.clear();
    		for (LeonDeBruxelles leon : restaurants.restaurants) {
    			if(Constantes.locationUser != null){
    				Location.distanceBetween(Constantes.locationUser.getLatitude(),Constantes.locationUser.getLongitude(), Double.parseDouble(leon.getLatitude()), Double.parseDouble(leon.getLongitude()), distance);
    				leon.setDistanceMeterFromUser(distance[0]);
    			}
    			lesLeons.add(leon);
    		}
    		adapterListLeon = new ListLeonAdapter(getActivity(), lesLeons);
    		listViewLeon.setAdapter(adapterListLeon);
    		loader.setVisibility(View.INVISIBLE);
    		loaderText.setVisibility(View.INVISIBLE);
    		Constantes.lesRestaurants = restaurants;
    	}
    }
    

    /** 
     * Implementation de LocationListener
     * Permet de récupérer la position GPS de l'utilisateur
     */
	@Override
	public void onLocationChanged(Location location) {
		float[] distance = new float[3];
		Constantes.locationUser = location;
		
		if(Constantes.lesRestaurants != null){
			for (LeonDeBruxelles leon : Constantes.lesRestaurants.restaurants ) {
				Location.distanceBetween(location.getLatitude(),location.getLongitude(), Double.parseDouble(leon.getLatitude()), Double.parseDouble(leon.getLongitude()), distance);
				leon.setDistanceMeterFromUser(distance[0]);
				lesLeons.add(leon);
			}
			adapterListLeon.updateLeonList(lesLeons);
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		if(status == LocationProvider.TEMPORARILY_UNAVAILABLE)
			Toast.makeText(getActivity(), "Le service de géolocalisation est temporairement indisponible", Toast.LENGTH_SHORT).show();
		if(status == LocationProvider.OUT_OF_SERVICE)
			Toast.makeText(getActivity(), "Le service de géolocalisation est indisponible", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
}