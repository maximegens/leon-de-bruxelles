package com.maxime.leondebruxelles.utils;

import java.text.DecimalFormat;

public class Conversion {

	public static float metreToKm(float metre){
		String result;
		DecimalFormat df = new DecimalFormat("#####.##");
		result = df.format(metre * 0.001);
		return Float.parseFloat(result.replace(',', '.'));
	}
}
