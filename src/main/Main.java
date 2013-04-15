package main;

public class Main {

	public static void main(String args[]){
		try{
			XMLParser parser = new XMLParser();
			parser.generateJUnit("c://RJR/teste.xml", "c://RJR/");
			System.out.println("File generated successfully.");	
		} catch (Exception e){
			System.out.println("Failure.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
