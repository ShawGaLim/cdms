package com.cdms.utils;

import java.util.HashSet;

public class RandomJudge {

	public static String [] createRandomJudge(HashSet<String> keyset, Integer num) {
		
		String[] keys = keyset.toArray(new String[0]);
		
		String temp;
		
		for(int i=0; i<num && i<keys.length; i++) {
			int ri = (int) (Math.random() * (keys.length-1));
			temp = keys[i];
			keys[i] = keys[ri];
			keys[ri] = temp;
		}
 		
		return keys;
	}
}
