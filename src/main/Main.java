package main;

import java.util.LinkedList;

public class Main {

public static void main(String args[]){
	
		
		//Código para testar o TestFileBuilder
		/*LinkedList<String> parameters0 = new LinkedList<String>();
		parameters0.add("p0");
		parameters0.add("p1");
		
		LinkedList<String> parameters1 = new LinkedList<String>();
		parameters1.add("p2");
		parameters1.add("p3");

		
		LinkedList<Attribute> variables0 = new LinkedList<Attribute>();
		variables0.add(new Attribute("tentativas","2"));
		variables0.add(new Attribute("numeroDePinos","4"));
		
		LinkedList<Attribute> variables1 = new LinkedList<Attribute>();
		variables1.add(new Attribute("tentativas","4"));
		variables1.add(new Attribute("numeroDePinos","10"));

		
		TestFileBuilder tf = new TestFileBuilder("JogoSenha", "gerarSenha");
		tf.addTestCase(variables0, parameters0);
		tf.addTestCase(variables1, parameters1);
		
		System.out.println(tf.buildTest());
		*/
		
		/***********************/
	
		XMLParser parser = new XMLParser();
			
		parser.generateJUnit(Constants.inputFilePath);
		System.out.println("File generated successfully.");
	}

}
