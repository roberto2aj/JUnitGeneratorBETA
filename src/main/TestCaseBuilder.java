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
	private List<Variable> attributes;	//Values of the attributes in the test case
	
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
							List<Variable> attributes,
							List<String> parameters){		
		this.objectName = new String (objectName);
		this.testCaseName = new String(testCaseName);
		this.methodName = new String (methodName);
		this.parameters = new LinkedList<String>(parameters);
		this.attributes = new LinkedList<Variable>(attributes);
		
	}


	/**
	 * Builds a string representing a test case according to the attributes of the TestCaseBuilder object
	 * @return A String object representing the test case method
	 */
	public String buildTestCase(){
		String testCase = "";
		testCase = testCase.concat("\t@Test\n");
		testCase = testCase.concat("\tpublic void " + testCaseName + "() {\n");
		
		testCase = testCase.concat(getAttributions());
		testCase = testCase.concat(getAssertions());				
			
		testCase = testCase.concat("\t}\n\n");
		
		return testCase;
	}


	/**
	 * Returns the lines containing the attributions to the test case.
	 */
	private String getAttributions(){
		String result = "";
		//Adds the changes of the values of the object to be tested using setter methods.
		for (int i = 0; i < attributes.size(); i++){
			Variable v = attributes.get(i);
			result = result.concat("\t\t" + objectName + ".set" + Constants.firstCharToUpperCase(v.getName()) + "(" + v.getValue() + ");\n");
		}
		if (!result.equals("")) {
			result = result.concat("\n");
		}
		return result;
	}

	/**
	 * Returns a String object with the lines containing the Assertions to the test case.
	 * Every assertion is added as a comment because it is expected that the user will complete the assertions with the desired values.
	 */
	private String getAssertions() {
		String result = "";
		result = result.concat("\t\t//Uncomment or add expected assertions here.\n"); 
		result = result.concat("\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n");

		for (int i = 0; i < attributes.size(); i++){
			Variable v = attributes.get(i);
			result = result.concat("\t\t//assertEquals(" + objectName + ".get" + Constants.firstCharToUpperCase(v.getName()) + "(), /* Add expected value here. */);\n");
		}

		result = result.concat("\t\t//assertEquals("+ objectName + "." + methodName+"(");
		for (int i = 0; i < parameters.size(); i++){
			result = result.concat(parameters.get(i));
			if (i < parameters.size() - 1){
				result = result.concat(", ");
			}
		}
		result = result.concat("), /* Add expected value here. */);\n");
		return result;
	}

}
