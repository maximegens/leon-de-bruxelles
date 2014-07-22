package com.maxime.leondebruxelles.activity;

import com.maxime.leondebruxelles.utils.Constantes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class PermissionGPSActivity extends Activity {

    protected void onCreate(Bundle paramBundle) {
    	
        super.onCreate(paramBundle);
        
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder
            .setMessage("Le GPS est inactif, voulez-vous l'activer ?")
            .setCancelable(false)
            .setPositiveButton("Activer GPS ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                        finish();
                    }
                }
            );
        localBuilder.setNegativeButton("Ne pas l'activer ",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    paramDialogInterface.cancel();
                    finish();
                    Constantes.CHOICE_TO_ACTIVATE_GPS = false;
                }
            }
        );
        localBuilder.create().show();
    }
}
