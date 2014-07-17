package com.maxime.leondebruxelles.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
 
public class HttpImageLeonTask extends AsyncTask<String, String, Bitmap> {
 
	protected void onPreExecute(){
        //Setup is done here
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        //TODO Auto-generated method stub
        try{
            URL url = new URL(params[0]);
            HttpURLConnection httpCon = 
            (HttpURLConnection)url.openConnection();
            if(httpCon.getResponseCode() != 200)
                throw new Exception("Failed to connect");
            InputStream is = httpCon.getInputStream();
            return BitmapFactory.decodeStream(is);
        }catch(Exception e){
            Log.e("Image","Failed to load image",e);
        }
        return null;
    } 
    protected void onProgressUpdate(Integer... params){
        //Update a progress bar here, or ignore it, it's up to you
    }
    protected void onPostExecute(Bitmap img){
//        ImageView iv = (ImageView)findViewById(R.id.remote_image);
//        if(iv!=null && img!=null){
//            iv.setImageBitmap(img);
//        }
    }
        protected void onCancelled(){
        	
        }
}