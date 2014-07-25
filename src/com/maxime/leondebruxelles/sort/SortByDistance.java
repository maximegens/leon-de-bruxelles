package com.maxime.leondebruxelles.sort;

import java.util.Comparator;

import com.maxime.leondebruxelles.beans.LeonDeBruxelles;

/**
 * Classe permettant de trier deux objets passé en paramétre.
 * @author Maxime Gens
 *
 */
public class SortByDistance implements Comparator<Object>{

	/**
	 * Compare les deux objets passé en paramétre.
	 */
    public int compare(Object o1, Object o2) {
        LeonDeBruxelles l1 = (LeonDeBruxelles) o1;
        LeonDeBruxelles l2 = (LeonDeBruxelles) o2; 
        return (l1.getDistanceMeterFromUser() > l2.getDistanceMeterFromUser() ? 1 : (l1.getDistanceMeterFromUser() == l2.getDistanceMeterFromUser() ? 0 : -1));
    }
}
