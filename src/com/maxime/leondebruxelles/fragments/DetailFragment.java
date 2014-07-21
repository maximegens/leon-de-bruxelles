package com.maxime.leondebruxelles.fragments;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.utils.Constantes;

public class DetailFragment extends Fragment {
	
    public final static String ARG_ID = "id";
    int mCurrentId = -1;
    TextView textViewNomLeon;
    TextView textViewCodePostaleVilleLeon;
    TextView textViewHoraires;
    TextView textViewTelLeon;
    ImageView imgViewPhoto;
    ImageView imgViewParking;
    ImageView imgViewAccesHandicape;
    ProgressBar loaderPhoto;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	View myInflatedView = inflater.inflate(R.layout.detail_view, container,false);
    	
        if (savedInstanceState != null) {
        	mCurrentId = savedInstanceState.getInt(ARG_ID);
        }
        
        textViewNomLeon = (TextView)myInflatedView.findViewById(R.id.detail_nom_leon);
        textViewCodePostaleVilleLeon = (TextView)myInflatedView.findViewById(R.id.detail_code_postale_ville_leon);
        textViewHoraires = (TextView)myInflatedView.findViewById(R.id.detail_horaires_leon);
        textViewTelLeon = (TextView)myInflatedView.findViewById(R.id.detail_tel_leon);
        imgViewPhoto = (ImageView)myInflatedView.findViewById(R.id.detail_photo_leon);
        imgViewParking = (ImageView)myInflatedView.findViewById(R.id.detail_parking_leon);
        imgViewAccesHandicape = (ImageView)myInflatedView.findViewById(R.id.detail_handicape_leon);
        loaderPhoto = (ProgressBar)myInflatedView.findViewById(R.id.detail_progress_bar_photo_leon);
        
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

    public void updateDetailView(int id) {
        
        LeonDeBruxelles leLeon = Constantes.lesRestaurants.getLeonById(id);
        
        RetreivePhotoLeonTask retreivePhotoLeonTask = new RetreivePhotoLeonTask();
        retreivePhotoLeonTask.execute(leLeon.getPhoto());
        
        textViewNomLeon.setText(Constantes.TITRE_LEON+" - "+leLeon.getNom());
        textViewCodePostaleVilleLeon.setText(leLeon.getCodePostal()+ " " +leLeon.getVille());
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
    		Bitmap bitmap = null;
            try{
                URL url = new URL(UrlPhoto);
                HttpURLConnection httpCon = 
                (HttpURLConnection)url.openConnection();
                if(httpCon.getResponseCode() != 200)
                    throw new Exception("Failed to connect");
                InputStream is = httpCon.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }catch(Exception e){
                Log.e("Image","Failed to load image",e);
            }
            return bitmap;
    		
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