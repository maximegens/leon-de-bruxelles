package com.maxime.leondebruxelles.utils;

import android.location.Location;

import com.maxime.leondebruxelles.beans.Restaurants;

/**
 * Constantes de l'application.
 * @author Maxime Gens
 *
 */
public class Constantes {
	
	public static final String URL_LEON_DE_BRUXELLES = "http://www.leon-de-bruxelles.fr/webservice/restaurant-service.php";
	public static final String LOCAL_LEON_DE_BRUXELLES = "leonDeBruxelles.json";
	public static final String TITRE_LEON = "Léon de Bruxelles";
	public static final float UN_KILOMETRE = 1000;
	public static boolean CHOICE_TO_ACTIVATE_GPS = true;
	public static Restaurants LES_RESTAURANTS = null;
	public static Location LOCATION_USER = null;

}
