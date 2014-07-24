package com.maxime.leondebruxelles.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;

import com.maxime.leondebruxelles.utils.Constantes;

public class AlertDialogCustom {

	Context context;
	
	public AlertDialogCustom(Context context){
		this.context = context;
	}
	
	 public void showWebSite(){
		 AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
	    	alertDialog
	            .setMessage("Voulez accéder au site internet \"Léon de Bruxelles\" ?")
	            .setCancelable(false)
	            .setPositiveButton("Oui",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	                    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.leon-de-bruxelles.fr/"));
	                    	context.startActivity(browserIntent);
	                    	
	                    }
	                }
	            );
	        alertDialog.setNegativeButton("Non",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	                    paramDialogInterface.cancel();
	                }
	            }
	        );
	        alertDialog.create().show();
	    }
	 
	 public void activateGPS(){
		 AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		 LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		 
		 if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		        localBuilder
	            .setMessage("Le GPS est inactif, voulez-vous l'activer ?")
	            .setCancelable(false)
	            .setPositiveButton("Activer GPS ",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	                        context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
	                    }
	                }
	            );
	        localBuilder.setNegativeButton("Ne pas l'activer ",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	                    paramDialogInterface.cancel();
	                    Constantes.CHOICE_TO_ACTIVATE_GPS = false;
	                }
	            }
	        );
		 }else{
			 localBuilder.setMessage("Le GPS est déjà activé")
			 			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		                    }
		                });
			 
		 }

	        localBuilder.create().show();
	    }
}
