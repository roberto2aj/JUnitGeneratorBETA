package main;

/**
 * This is a simples class representing a variable, containing its name and value represented as a String.
 * It only has getter methods and the only way to set its attributes is through its constructor.
 * @author RobertoJr
 *
 */
public class Variable {

	private String name;
	private String value;
	
	public Variable(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
	}
}
