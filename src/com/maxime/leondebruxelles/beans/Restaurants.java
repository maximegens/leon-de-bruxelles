package com.maxime.leondebruxelles.beans;

import java.util.ArrayList;

public class Restaurants{

	public ArrayList<LeonDeBruxelles> restaurants;

	public LeonDeBruxelles getLeonById(int id){
		for (LeonDeBruxelles leon : restaurants) {
			if(leon.getId() == id)
				return leon;
		}
		return null;
	}
	
	
	

}
