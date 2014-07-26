package com.maxime.leondebruxelles.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.text.Html;

import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.utils.Constantes;

/**
 * Classe contenant l'ensemble des AlertDialogs de l'application.
 * @author Maxime Gens
 *
 */
public class AlertDialogCustom {

	Context context;
	
	/**
	 * Constructeur de la class AlertDialogCustom
	 * @param context Le contexte de l'activity dans laquelle afficher l'AlertDialog
	 */
	public AlertDialogCustom(Context context){
		this.context = context;
	}
	
	public void share(int idLeon){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		if(idLeon == -1)
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Télécharger l'Application Léon de Bruxelles sur le Play Store");
		else{
			LeonDeBruxelles leon = Constantes.LES_RESTAURANTS.getLeonById(idLeon);
			String content = "Léon de Bruxelles "+leon.getVille()+"<br/>"+leon.getInfosSupplementaires();
			sendIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(content));
		}
		sendIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(sendIntent,"Partager..." ));
	}
	/**
	 * Affiche une AlertDialog proposant d'ouvrir une page internet vers le site Léon de Bruxelles.
	 */
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
	 
	 /**
	  * Affiche une AlertDialog proposant d'activer le GPS si celui-i n'est pas activé.
	  * Sinon indique sur le GPS est activé. 
	  */
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