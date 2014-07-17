package com.maxime.leondebruxelles.beans;

import java.util.ArrayList;

public class Restaurants {

	private ArrayList<LeonDeBruxelles> lesRestaurants;

	public Restaurants(ArrayList<LeonDeBruxelles> lesRestaurants) {
		super();
		this.lesRestaurants = lesRestaurants;
	}

	public ArrayList<LeonDeBruxelles> getLesRestaurants() {
		return lesRestaurants;
	}

	public void setLesRestaurants(ArrayList<LeonDeBruxelles> lesRestaurants) {
		this.lesRestaurants = lesRestaurants;
	}
	
	
}
