package com.maxime.leondebruxelles.fragments;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.activity.AlertDialogCustom;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.utils.Connection;
import com.maxime.leondebruxelles.utils.Constantes;

/**
 * Fragment Detail : classe représentante la vue affichant le détails d'un Léon de Bruxelles.
 * @author Maxime Gens
 *
 */
public class DetailFragment extends Fragment {
	
    public final static String ARG_ID = "id";
    public final static String ARG_PICTURE_LEON = "pictureLeon";
    boolean isConnected;
    int mCurrentIdLeon = -1;
    RelativeLayout myLayout;
    Bitmap pictureLeon = null;
    TextView textViewSelectionLeon;
    TextView textViewNomLeon;
    TextView textViewAdresseCompleteLeon;
    TextView textViewHoraires;
    TextView textViewTelLeon;
    TextView textViewInformationsLeon;
    ImageView imgViewPhoto;
    ImageView imgViewtel;
    ImageView imgViewParking;
    ImageView imgViewAccesHandicape;
    MapFragment map;
    ProgressBar loaderPhoto;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	View myInflatedView = inflater.inflate(R.layout.detail_view, container,false);
    	myLayout = new RelativeLayout(getActivity());

        if (savedInstanceState != null) {
        	mCurrentIdLeon = savedInstanceState.getInt(ARG_ID);
        	pictureLeon = savedInstanceState.getParcelable(ARG_PICTURE_LEON);
        }
        
        textViewSelectionLeon = (TextView)myInflatedView.findViewById(R.id.detail_selection_leon);
        textViewNomLeon = (TextView)myInflatedView.findViewById(R.id.detail_nom_leon);
        textViewAdresseCompleteLeon = (TextView)myInflatedView.findViewById(R.id.detail_adrese_complete_leon);
        textViewHoraires = (TextView)myInflatedView.findViewById(R.id.detail_horaires_leon);
        textViewTelLeon = (TextView)myInflatedView.findViewById(R.id.detail_tel_leon);
        textViewInformationsLeon = (TextView)myInflatedView.findViewById(R.id.detail_informations);
        imgViewPhoto = (ImageView)myInflatedView.findViewById(R.id.detail_photo_leon);
        imgViewParking = (ImageView)myInflatedView.findViewById(R.id.detail_parking_leon);
        imgViewAccesHandicape = (ImageView)myInflatedView.findViewById(R.id.detail_handicape_leon);
        imgViewtel = (ImageView)myInflatedView.findViewById(R.id.detail_image_tel);
        loaderPhoto = (ProgressBar)myInflatedView.findViewById(R.id.detail_progress_bar_photo_leon);
    	
    	if(pictureLeon != null)
    		imgViewPhoto.setImageBitmap(pictureLeon);
    	
    	imgViewtel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent telIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + textViewTelLeon.getText().toString()));
				startActivity(telIntent);
			}
		});
    	
        return myInflatedView;
    }
    
    
    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
        	updateDetailView(args.getInt(ARG_ID));
        } else if (mCurrentIdLeon != -1) {
        	updateDetailView(mCurrentIdLeon);
        }
    }
    
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       if(menu != null){
    	   menu.findItem(R.id.menu_share).setVisible(true);
       }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	AlertDialogCustom alert = new AlertDialogCustom(getActivity());
    	switch (item.getItemId()) {
    	case R.id.menu_share :
    		alert.share(mCurrentIdLeon);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }

    /**
     * Mise à jour de la vue représentant le détail du Léon de Bruxelles correspond à l'ID passé en paramétre.
     * @param id L'ID du restraurant à afficher.
     */
    public void updateDetailView(int id) {
    	
    	textViewSelectionLeon.setVisibility(View.INVISIBLE);
    	
        LeonDeBruxelles leLeon = Constantes.LES_RESTAURANTS.getLeonById(id);
        isConnected = Connection.isConnectedInternet(getActivity());
        
        if(Constantes.NB_PANEL == 2)
        	pictureLeon = null;
        
        imgViewPhoto.setVisibility(View.VISIBLE);
        
        if(isConnected && pictureLeon == null)
        {
        	RetreivePhotoLeonTask retreivePhotoLeonTask = new RetreivePhotoLeonTask();
            retreivePhotoLeonTask.execute(leLeon.getPhoto());
        }
        
        textViewNomLeon.setText(Html.fromHtml(Constantes.TITRE_LEON+" <br/> "+leLeon.getNom()));
        textViewAdresseCompleteLeon.setText(leLeon.getAdresse() +" - "+ leLeon.getCodePostal()+ " " +leLeon.getVille());
        textViewHoraires.setText(Html.fromHtml(leLeon.getInfosSupplementaires()));
        textViewTelLeon.setText(leLeon.getTelephone());
           
        if(leLeon.getParking().equals("1")){
        	imgViewParking.setVisibility(View.VISIBLE);
        }
        if(leLeon.getAccesHandicape().equals("1"))
        	imgViewAccesHandicape.setVisibility(View.VISIBLE);
        
        textViewHoraires.setVisibility(View.VISIBLE);
        textViewInformationsLeon.setVisibility(View.VISIBLE);
        imgViewtel.setVisibility(View.VISIBLE);
        
/** Ce code fonctionne sous Tablette **/
/**      GoogleMap map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	LatLng positionLeon = new LatLng(Double.parseDouble(leLeon.getLatitude()),Double.parseDouble(leLeon.getLongitude()));
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLeon, 13));
        map.addMarker(new MarkerOptions()
                .title(leLeon.getNom())
                .snippet("The most populous city in Australia.")
                .position(positionLeon));
**/    
        
        mCurrentIdLeon = id;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_ID, mCurrentIdLeon);
        outState.putParcelable(ARG_PICTURE_LEON, pictureLeon);
    }
    
    /**
     * AsyncTack : Télécharge l'image représentatif du restaurant.
     * 
     */
    private class RetreivePhotoLeonTask extends AsyncTask<String, String, Bitmap> {
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		loaderPhoto.setVisibility(View.VISIBLE);
    	}

    	@Override
    	protected Bitmap doInBackground(String... params) {
    		String UrlPhoto = params[0];
    		
            try{
                URL url = new URL(UrlPhoto);
                HttpURLConnection httpCon = 
                (HttpURLConnection)url.openConnection();
                if(httpCon.getResponseCode() != 200)
                    throw new Exception("Failed to connect : responce is not 200");
                InputStream is = httpCon.getInputStream();
                pictureLeon = BitmapFactory.decodeStream(is);
                return pictureLeon;
            }catch(Exception e){
                Log.e("Image","Failed to load image",e);
            }
            return pictureLeon;
    		
    	}
    	@Override
    	protected void onPostExecute(Bitmap bitmap) {
    		if(bitmap != null){
    			imgViewPhoto.setImageBitmap(bitmap);
    		}
    		loaderPhoto.setVisibility(View.INVISIBLE);
    	}
    }
}