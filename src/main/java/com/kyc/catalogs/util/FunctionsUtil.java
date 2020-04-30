package com.kyc.catalogs.util;

public class FunctionsUtil {
	
	
	public static Integer strMustInteger(String cad) {
		
		Integer result = 0;
		
		if(cad!=null && !cad.isEmpty() && strIsNumeric(cad)) {
			
			result= Integer.valueOf(cad);
		}
		
		return result;
	}
	
	public static boolean strIsNumeric(String cad) {
		
		if(cad!=null) {
			return cad.matches("[\\d]+");
		}
		return false;
		
	}

}
