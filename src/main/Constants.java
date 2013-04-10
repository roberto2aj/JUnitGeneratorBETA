package main;

/**
 * Class containing static methods and constants to be used throughout the system.
 * @author RobertoJr
 *
 */
public class Constants {
	
	public static final String inputFilePath = "c://RJR/teste.xml";
	public static final String outputFilePathPrefix = "c://RJR/";
	

	/**
	 * Changes the first char of a String to lower case. 
	 * In the special cases of a null String or a String with length equal to zero, no change is made. 
	 * @param s The String to be changed.
	 * @return The result String after the change.
	 */
	public static String firstCharToLowerCase(String s){
		if (s != null && s != ""){
			String result = Character.toString(Character.toLowerCase(s.charAt(0))).concat(s.substring(1));
			return result;
		} else {
			return s;
		}
	}

	/**
	 * Changes the first char of a String to upper case.
	 * In the special cases of a null String or a String with length equal to zero, no change is made.
	 * @param s The String to be changed.
	 * @return The result String after the change.
	 */
	public static String firstCharToUpperCase(String s){
		if (s != null && s != ""){
			String result = Character.toString(Character.toUpperCase(s.charAt(0))).concat(s.substring(1));
			return result;
		} else {
			return s;
		}
	}
}
