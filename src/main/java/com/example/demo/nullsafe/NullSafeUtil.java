package com.example.demo.nullsafe;

public class NullSafeUtil {
		
	 public static double get(Double value) {
	        return value != null ? value : 0.0;
	    }

	    public static int get(Integer value) {
	        return value != null ? value : 0;
	    }

	    public static String get(String value) {
	        return value != null ? value : "";
	    }
}
