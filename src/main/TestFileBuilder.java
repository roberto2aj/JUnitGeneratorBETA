package main;

import java.util.LinkedList;
import java.util.List;

public class TestFileBuilder {

	private String className;					//Name of the class to be tested.
	private String objectName;					//Name of the object of the class to be tested containin
	private String methodName; 					//Name of the method to be tested
	private List<TestCaseBuilder> testCases;	//List containing the data of each test case.
	
	public TestFileBuilder(String className, String methodName){
		this.className = className;
		this.objectName = new String(className);
		this.objectName = Constants.firstCharToLowerCase(this.objectName);
		this.methodName = new String (methodName);
		this.testCases = new LinkedList<TestCaseBuilder>();
	}

	/**
	 * Builds a test case file based on the values of the attributes of the instance of the TestFileBuild object and
	 * returns a String object representing a JUnit test file.
	 * @return A string object representing a JUnit test file.
	 */
	public String buildTest(){
		String test = "";
		test = test.concat("import org.junit.*;\n");
		test = test.concat("import org.junit.Assert.*;\n");
		test = test.concat("import org.junit.Before;\n");
		test = test.concat("import org.junit.Test;\n\n");
		
		test = test.concat("public class " + className + "Test {\n\n");
		
		test = test.concat("\tprivate " + className + " " + objectName + ";\n\n");

		test = test.concat("\t@Before\n");
		test = test.concat("\tpublic void setUp() throws Exception {\n");
		test = test.concat("\t\t" + objectName + " = new " + className + "();\n");
		test = test.concat("\t}\n\n");
		
		for (int i = 0; i < testCases.size(); i++){
			test = test.concat(testCases.get(i).buildTestCase());
		}
		
		test = test.concat("}");
		return test;
	}

	/**
	 * Adds a new TestCaseBuilder object to the list of TestCaseBuilders based on the parameters and attributes of the test case.
	 * @param attributes The values of the attributes of the class during the beginning of the test case.
	 * @param parameters The names and values of the parameters of the test case.
	 */
	public void addTestCase(List<Variable> attributes, List<String> parameters){
		int id = testCases.size() + 1;
		TestCaseBuilder tcb = new TestCaseBuilder (objectName, methodName + "Test" + id, methodName, attributes, parameters);
		testCases.add(tcb);
	}
	
}
