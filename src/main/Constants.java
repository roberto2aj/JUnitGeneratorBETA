package main;

/**
 * Class containing static methods and constants to be used throughout the system.
 * @author RobertoJr
 *
 */
public class Constants {

	/**
	 * Changes the first char of a String to lower case.
	 * @param s The String to be changed.
	 * @return The result String after the change.
	 */
	public static String firstCharToLowerCase(String s){
		String result = Character.toString(Character.toLowerCase(s.charAt(0))).concat(s.substring(1));
		return result;
	}

	/**
	 * Changes the first char of a String to upper case.
	 * @param s The String to be changed.
	 * @return The result String after the change.
	 */
	public static String firstCharToUpperCase(String s){
		String result = Character.toString(Character.toUpperCase(s.charAt(0))).concat(s.substring(1));
		return result;
	}
}
