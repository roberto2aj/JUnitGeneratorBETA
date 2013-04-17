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
		String methodsCode = "";
		for (int i = 0; i < testCases.size(); i++){
			methodsCode = methodsCode.concat(getTestCase(i));
		}
		String test = "import org.junit.*;\n"
					+ "import static org.junit.Assert.*;\n"
					+ "import org.junit.Before;\n"
					+ "import org.junit.Test;\n" 
					+ "\n"
					+ "public class " + className + Constants.firstCharToUpperCase(methodName) + "Test {\n" 
					+ "\n"
					+ "\tprivate " + className + " " + objectName + ";\n"
					+ "\n"
					+ "\t@Before\n"
					+ "\tpublic void setUp() throws Exception {\n"
					+ "\t\t" + objectName + " = new " + className + "();\n"
					+ "\t}\n" 
					+ "\n"
					+ methodsCode
					+ "}";
		return test;
	}

	/**
	 * Returns a String representing a test case method based on its index in the list testCases.
	 * @param index The index of the test case.
	 * @return A string representing the test case method.
	 */
	private String getTestCase(int index){
		return testCases.get(index).buildTestCase();
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
