package com.maxime.leondebruxelles.utils;

import android.location.Location;

import com.maxime.leondebruxelles.beans.Restaurants;

/**
 * Application contantes
 * @author Maxime Gens
 *
 */
public class Constantes {
	
	public static final String URL_LEON_DE_BRUXELLES = "http://www.leon-de-bruxelles.fr/webservice/restaurant-service.php";
	public static final String LOCAL_LEON_DE_BRUXELLES = "leonDeBruxelles.json";
	public static final String TITRE_LEON = "Léon de Bruxelles";
	public static final float ONE_KILOMETER = 1000;
	public static boolean CHOICE_TO_ACTIVATE_GPS = true;
	public static Restaurants lesRestaurants = null;
	public static Location locationUser = null;

}
