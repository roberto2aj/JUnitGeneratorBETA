package main;

import java.util.LinkedList;
import java.util.List;

/**
 * Class which creates the method for a test case in JUnit
 * @author RobertoJr
 *
 */
public class TestCaseBuilder {

	private String objectName; 			//Name of the object to be tested
	private String testCaseName;		//Nome of the method used in the test case
	private String methodName;			//Nome of the method to be tested
	private List<String> parameters;	//Values of the parameters
	private List<Attribute> attributes;	//Values of the attributes in the test case
	
	/**
	 * Constructor of the class
	 * @param objectName The name of the instance of the class to be tested in the JUnit class
	 * @param testCaseName The name of the method of the test case in the JUnit class
	 * @param methodName The name of the method of the class to be tested to be called 
	 * @param attributes The names and the values of the attributes in the test case
	 * @param parameters The values of the parameters
	 */
	public TestCaseBuilder(String objectName, 
							String testCaseName,
							String methodName,
							List<Attribute> attributes,
							List<String> parameters){
		
		this.objectName = new String (objectName);
		this.testCaseName = new String(testCaseName);
		this.methodName = new String (methodName);
		this.parameters = new LinkedList<String>(parameters);
		this.attributes = new LinkedList<Attribute>(attributes);
		
	}


	/**
	 * Builds a string representing a test case according to the attributes of the TestCaseBuilder object
	 * @return A String object representing the test case method
	 */
	public String buildTestCase(){
		String testCase = "";
		testCase = testCase.concat("\t@Test\n");
		testCase = testCase.concat("\tpublic void " + testCaseName + "() {\n");
		
		testCase = addAttributions(testCase);
		testCase = addAssertions(testCase);				
			
		testCase = testCase.concat("\t}\n\n");
		
		return testCase;
	}


	/**
	 * Adds the lines containing the attributions to the test case. This method is used as part of the BuildTestCase method.
	 */
	private String addAttributions(String s){
		//Adds the changes of the values of the object to be tested using setter methods.
		for (int i = 0; i < attributes.size(); i++){
			Attribute v = attributes.get(i);
			s = s.concat("\t\t" + objectName + ".set" + Constants.firstCharToUpperCase(v.getName()) + "(" + v.getValue() + ");\n");
		}
		s = s.concat("\n");
		return s;
	}

	/**
	 * Adds the lines containing the Assertions to the test case. Every assertion is added in commented form because it is incomplete in its current form
	 * as the expected values shall be supplied by the user. This method is used as part of the BuildTestCase method.
	 */
	private String addAssertions(String testCase) {
		testCase = testCase.concat("\t\t//Uncomment or add expected assertions here.\n"); 
		testCase = testCase.concat("\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n");

		for (int i = 0; i < attributes.size(); i++){
			Attribute v = attributes.get(i);
			testCase = testCase.concat("\t\t//assertEquals(" + objectName + ".get" + Constants.firstCharToUpperCase(v.getName()) + "), /* Add expected value here. */);\n");
		}

		testCase = testCase.concat("\t\t//assertEquals("+ objectName + "." + methodName+"(");
		for (int i = 0; i < parameters.size(); i++){
			testCase = testCase.concat(parameters.get(i));
			if (i < parameters.size() - 1){
				testCase = testCase.concat(",");
			}
		}
		testCase = testCase.concat("), /* Add expected value here. */);\n");
		return testCase;
	}

}
