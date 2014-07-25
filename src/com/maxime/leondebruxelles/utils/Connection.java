package com.maxime.leondebruxelles.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * Informe sur le status internet du device.
 * 
 */
public class Connection
{
	/**
	 * Indique si le device est connecté à Internet
	 * @param activity L'activity qui demande le test de connection à internet
	 * @return boolean indique si le device est connecté ou non.
	 */
    public static boolean isConnectedInternet(Activity activity)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            State networkState = networkInfo.getState();
            if (networkState.compareTo(State.CONNECTED) == 0)
            {
                return true;
            }
            else return false;
        }
        else return false;
    }
}