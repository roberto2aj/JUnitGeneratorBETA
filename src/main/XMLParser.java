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
Expected XML format
<test-suite>
	<machine-name>Player</machine-name>
	<operation-under-test>substitute</operation-under-test>
	<partition-strategy>Equivalence Classes</partition-strategy>
	<combinatorial-criteria>All-Combinations</combinatorial-criteria>
	<test-cases>
		<test-case>
			<id>1</id>
			<formula>Formula example here</formula>
			<state-variables>
				<variable>
					<identifier>var1</identifier>
					<values>
						<value>1</value>
					</values>
				</variable>
				<variable>
					<identifier>var2</identifier>
					<values>
						<value>PLAYER1</value>
						<value>PLAYER2</value>
						<value>PLAYER3</value>
					</values>
				</variable>
			</state-variables>
			<operation-parameters>
				<parameter>
					<identifier>param1</identifier>
					<values>
						<value>1</value>
					</values>
				</parameter>
				<parameter>
					<identifier>param2</identifier>
					<values>
						<value>PLAYER1</value>
						<value>PLAYER2</value>
						<value>PLAYER3</value>
					</values>
				</parameter>
			</operation-parameters>
		</test-case>
	</test-cases>
</test-suite>
*/



public class XMLParser {

	public XMLParser (){ 
	}

	/**
	 * Generates a JUnit test file template based on a XML file which path is given
	 * by the input fileName. The result file is written on outputPath. 
	 * @param fileName The path to the XML file.
	 * @param outputPath The path to the folder in which the JUnit file will be written.  There
	 * is no need to specify the name of the file, which is generated automatically based on the
	 * name of the abstract machine. 
	 */
	public void generateJUnit(String fileName, String outputPath){
		String content = parseFile(fileName);
		String outputName = getOutputName(fileName);
		
		writeJUnitFile(outputPath + outputName, content);
	
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
			Element root = doc.getRootElement();		 //Gets the test-suite node.
						
			String className = root.getFirstChildElement("machine-name").getValue();
			String operationName = root.getFirstChildElement("operation-under-test").getValue();			
			TestFileBuilder tf = new TestFileBuilder(className, operationName);
			
			Elements testCasesXML = root.getFirstChildElement("test-cases").getChildElements("test-case");

			//For each test case...
			for(int i = 0; i < testCasesXML.size(); i++){
				LinkedList<String> parameters = new LinkedList<String>();
				LinkedList<Variable> attributes = new LinkedList<Variable>();
				Element testCaseXML = testCasesXML.get(i);

				//Mount its list of attributes
				Elements attributesXML = testCaseXML.getFirstChildElement("state-variables").getChildElements("variable");
				for(int j = 0; j < attributesXML.size(); j++){
					String value = attributesXML.get(j).getFirstChildElement("values").getFirstChildElement("value").getValue();
					String name = attributesXML.get(j).getFirstChildElement("identifier").getValue();
					attributes.add(new Variable(name, value));
				}

				//Mount its list of parameters
				Elements parametersXML = testCaseXML.getFirstChildElement("operation-parameters").getChildElements("parameter"); 
				for (int j = 0; j < parametersXML.size(); j++){
					parameters.add(parametersXML.get(j).getFirstChildElement("values").getFirstChildElement("value").getValue());
				}
				
				//And add a new test case using the parameters and attributes found to the TestFileBuilder tf
				tf.addTestCase(attributes, parameters);
			}
			
			return tf.buildTest();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
			fnfe.printStackTrace();
			System.exit(-1);
		} catch (UnsupportedEncodingException uee) {
			System.out.println("Unsupported encoding.");
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
		} catch (Exception e) {
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

			String outputFileName = root.getFirstChildElement("machine-name").getValue() + "Test.java";
			return outputFileName;
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
			fnfe.printStackTrace();
			System.exit(-1);
		} catch (UnsupportedEncodingException uee) {
			System.out.println("Unsupported encoding.");
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
		FileOutputStream fout;		
	
		try {
			fout = new FileOutputStream (outputFileName);	
			new PrintStream(fout).println (content);		
			fout.close();		    
		} catch (IOException e) {
			System.err.println ("Error: could not write to file.");
			System.exit(-1);
		}
	}

}