package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

/*
Formato de XML esperado. 

<machine-name>Player</machine-name>
<operation-under-test>substitute</operation-under-test>
<partition-strategy>Equivalence Classes</partition-strategy>
<combinatorial-criteria>All-Combinations</combinatorial-criteria>

<test-case>
	<variable>
		<identifier>var1</identifier>
		<value>1</value>
	</variable>

	<variable>
		<identifier>var2</identifier>
		<value>3</value>
	</variable>

	<operation-parameters>
		<value>1</value>
		<value>2</value>
	</operation-parameters>
</test-case> 
 */

//TODO adicionar um método para definir o nome do arquivo de teste gerado com base no XML

public class XMLParser {

	private static String inputFileName;
	private static String content;
	private String outputFileName;


	public XMLParser (){
		this.inputFileName = ""; 
	}

	public void generateJUnit(String fileName){
		String content = parseFile(fileName);
		String outputName = getOutputName(fileName);
		
		writeJUnitFile(outputName, content);
	
	}

	/**
	 * Parses a xml file and generates a JUnit test class in a String format
	 * @param fileName The name of the xml file
	 * @return The content of the JUnit file
	 */
	public static String parseFile(String fileName){
		try {

			FileInputStream xmlFile = new FileInputStream(fileName);

			Builder builder = new Builder();
			Document doc = builder.build(xmlFile);
			Element root = doc.getRootElement();

			String className = root.getAttributeValue("machine-name");
			String operationName = root.getAttributeValue("operation-under-test");
			TestFileBuilder tf = new TestFileBuilder(className, operationName);
			
			Elements testCasesXML = root.getChildElements("test-case");

			//For each test case...
			for(int i = 0; i < testCasesXML.size(); i++){
				LinkedList<String> parameters = new LinkedList<String>();
				LinkedList<Attribute> attributes = new LinkedList<Attribute>();
				Element testCaseXML = testCasesXML.get(i);
				
				//Mount its list of attributes
				Elements attributesXML = testCaseXML.getChildElements("variable");
				for(int j = 0; j < attributesXML.size(); j++){
					String value = attributesXML.get(j).getAttributeValue("value");
					String name = attributesXML.get(j).getAttributeValue("identifier");
					attributes.add(new Attribute(name, value));
				}
				
				
				//Mount its list of parameters
				Elements parametersXML = testCaseXML.getChildElements("operation-parameters"); 
				for (int j = 0; j < parametersXML.size(); j++){
					parameters.add(parametersXML.get(j).getAttributeValue("value"));
				}
				
				//And add a new test case using the parameters and attributes found to the TestFileBuilder tf
				tf.addTestCase(attributes, parameters);
			}
			
			return tf.buildTest();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Arquivo não encontrado.");
			fnfe.printStackTrace();
			System.exit(-1);
		} catch (UnsupportedEncodingException uee) {
			System.out.println("Arquivo com formação não suportada.");
			uee.printStackTrace();
			System.exit(-1);
		} catch (IOException ioe) {
			System.out.println("IO Exception");
			ioe.printStackTrace();
			System.exit(-1);
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return "error"; 
	}

	/**
	 * Gets the name of the output file to be generated based on the name of the Abstract Machine.
	 * @param inputFileName The name of the .xml file to be read
	 * @return The name of the output file.
	 */
	private String getOutputName(String inputFileName){
		try {

			FileInputStream xmlFile = new FileInputStream(inputFileName);

			Builder builder = new Builder();
			Document doc = builder.build(xmlFile);
			Element root = doc.getRootElement();

			String outputFileName = root.getAttributeValue("machine-name") + "Test.java";
			return outputFileName;
		} catch (FileNotFoundException fnfe) {
			System.out.println("Arquivo não encontrado.");
			fnfe.printStackTrace();
			System.exit(-1);
		} catch (UnsupportedEncodingException uee) {
			System.out.println("Arquivo com formação não suportada.");
			uee.printStackTrace();
			System.exit(-1);
		} catch (IOException ioe) {
			System.out.println("IO Exception");
			ioe.printStackTrace();
			System.exit(-1);
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return "error.java"; 

	}
	
	
	/**
	 * Creates or overwrites a JUnit test class
	 * @param outputFileName The name of the file to be created or overwritten
	 * @param content The content to be written on the file
	 */
	private void writeJUnitFile(String outputFileName, String content){
		// Stream para escrever no arquivo
		FileOutputStream fout;		
	
		try {
			fout = new FileOutputStream (outputFileName);	// Abre um stream de saída.
			new PrintStream(fout).println (content);		// Imprime o log no arquivo
			fout.close();									// fecha o stream de saída		    
			System.out.println("Arquivo de teste " + outputFileName + " gerado com sucesso.");
		} catch (IOException e) {
			System.err.println ("Erro: não foi possível escrever no arquivo.");
			System.exit(-1);
		}
	}

}