package com.maxime.leondebruxelles.beans;

import java.util.ArrayList;

/**
 * Bean restaurants. Cette class contient un objet ArrayList permettant de référencer la liste des restaurants.
 * Classe principalement utilisée pour le GSON et la Constantes contenant la liste des restaurants.
 * @author Maxime Gens
 *
 */
public class Restaurants{

	public ArrayList<LeonDeBruxelles> restaurants;

	/**
	 * Retourne le restaurant correspondant à l'ID passé en paramétre.
	 * @param id L'ID du restaurant.
	 * @return Le Léon de Bruxelles correspondant à l'ID.
	 */
	public LeonDeBruxelles getLeonById(int id){
		for (LeonDeBruxelles leon : restaurants) {
			if(leon.getId() == id)
				return leon;
		}
		return null;
	}
}
