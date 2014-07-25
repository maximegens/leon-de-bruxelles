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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.utils.Connection;
import com.maxime.leondebruxelles.utils.Constantes;

public class DetailFragment extends Fragment {
	
    public final static String ARG_ID = "id";
    public final static String ARG_PICTURE_LEON = "pictureLeon";
    int mCurrentId = -1;
    TextView textViewNomLeon;
    TextView textViewAdresseCompleteLeon;
    TextView textViewHoraires;
    TextView textViewTelLeon;
    ImageView imgViewPhoto;
    ImageView imgViewParking;
    ImageView imgViewAccesHandicape;
    ProgressBar loaderPhoto;
    ImageView imageTel;
    boolean isConnected;
    Bitmap pictureLeon = null;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	View myInflatedView = inflater.inflate(R.layout.detail_view, container,false);
    	
        if (savedInstanceState != null) {
        	mCurrentId = savedInstanceState.getInt(ARG_ID);
        	pictureLeon = savedInstanceState.getParcelable(ARG_PICTURE_LEON);
        }
        
        textViewNomLeon = (TextView)myInflatedView.findViewById(R.id.detail_nom_leon);
        textViewAdresseCompleteLeon = (TextView)myInflatedView.findViewById(R.id.detail_adrese_complete_leon);
        textViewHoraires = (TextView)myInflatedView.findViewById(R.id.detail_horaires_leon);
        textViewTelLeon = (TextView)myInflatedView.findViewById(R.id.detail_tel_leon);
        imgViewPhoto = (ImageView)myInflatedView.findViewById(R.id.detail_photo_leon);
        imgViewParking = (ImageView)myInflatedView.findViewById(R.id.detail_parking_leon);
        imgViewAccesHandicape = (ImageView)myInflatedView.findViewById(R.id.detail_handicape_leon);
        loaderPhoto = (ProgressBar)myInflatedView.findViewById(R.id.detail_progress_bar_photo_leon);
    	imageTel = (ImageView)myInflatedView.findViewById(R.id.detail_image_tel);
    	
    	if(pictureLeon != null)
    		imgViewPhoto.setImageBitmap(pictureLeon);
    	
        imageTel.setOnClickListener(new OnClickListener() {
			
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
        } else if (mCurrentId != -1) {
        	updateDetailView(mCurrentId);
        }
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void updateDetailView(int id) {
        
        LeonDeBruxelles leLeon = Constantes.lesRestaurants.getLeonById(id);
        isConnected = Connection.isConnectedInternet(getActivity());
        if(isConnected && pictureLeon == null)
        {
        	RetreivePhotoLeonTask retreivePhotoLeonTask = new RetreivePhotoLeonTask();
            retreivePhotoLeonTask.execute(leLeon.getPhoto());
        }
        
        textViewNomLeon.setText(Html.fromHtml(Constantes.TITRE_LEON+" <br/> "+leLeon.getNom()));
        textViewAdresseCompleteLeon.setText(leLeon.getAdresse() +" - "+ leLeon.getCodePostal()+ " " +leLeon.getVille());
        textViewHoraires.setText(Html.fromHtml(leLeon.getInfosSupplementaires()));
        textViewTelLeon.setText(leLeon.getTelephone());
           
        if(leLeon.getParking().equals("1"))
        	imgViewParking.setVisibility(View.VISIBLE);
        if(leLeon.getAccesHandicape().equals("1"))
        	imgViewAccesHandicape.setVisibility(View.VISIBLE);
        
        mCurrentId = id;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_ID, mCurrentId);
        outState.putParcelable(ARG_PICTURE_LEON, pictureLeon);
    }
    
    /**
     * Récupére la liste des Leons de Bruxelles pour les afficher à l'utilisateur.
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
                    throw new Exception("Failed to connect");
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