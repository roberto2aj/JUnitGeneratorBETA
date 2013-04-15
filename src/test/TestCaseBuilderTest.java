package test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Constants;
import main.TestCaseBuilder;
import main.Variable;



/**
 * Test class for the TestCaseBuilder.java
 * This class does not test TestCaseBuilder.buildTestCase() as all it does is concatenating the results of
 * getAttributions(), getAssertions(), the name of the test case and some other pieces of text in order to create
 * a String representing a test case method. 
 * @author RobertoJr
 *
 */
public class TestCaseBuilderTest {

	private TestCaseBuilder tcb;
	
	@Before
	public void setUp() throws Exception {
	}

	//No variables
	@Test
	public void getAttributionsTest1(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		List<String> parameters = new LinkedList<String>();
		parameters.add("par1");
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAttributions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//1 variable
	@Test
	public void getAttributionsTest2(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		attributes.add(new Variable("variable1", "value1"));
		List<String> parameters = new LinkedList<String>();
		parameters.add("par1");
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "\t\tplayer.setVariable1(value1);\n\n";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAttributions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	//2 variables
	@Test
	public void getAttributionsTest3(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		attributes.add(new Variable("variable1", "value1"));
		attributes.add(new Variable("variable2", "value2"));
		List<String> parameters = new LinkedList<String>();
		parameters.add("par1");
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "\t\tplayer.setVariable1(value1);\n"
					  +	"\t\tplayer.setVariable2(value2);\n\n";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAttributions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	//No variables
	//No parameters
	@Test
	public void getParametersTest1(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		List<String> parameters = new LinkedList<String>();
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.play(), /* Add expected value here. */);\n";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAssertions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	

	//1 variable
	//1 parameter
	@Test
	public void getParametersTest2(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		attributes.add(new Variable("variable1", "value1"));
		List<String> parameters = new LinkedList<String>();
		parameters.add("2");
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.getVariable1(), /* Add expected value here. */);\n"
					  + "\t\t//assertEquals(player.play(2), /* Add expected value here. */);\n";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAssertions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);		    
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	

	//2 variable
	//2 parameter
	@Test
	public void getParametersTest3(){
		String objectName = "player";
		String testCaseName = "playTest1";
		String methodName = "play";
		List<Variable> attributes = new LinkedList<Variable>();
		attributes.add(new Variable("variable1", "value1"));
		attributes.add(new Variable("variable2", "value2"));
		List<String> parameters = new LinkedList<String>();
		parameters.add("2");
		parameters.add("3");
		
		tcb = new TestCaseBuilder(objectName, testCaseName, methodName, attributes, parameters);		
		String oracle = "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.getVariable1(), /* Add expected value here. */);\n"
					  + "\t\t//assertEquals(player.getVariable2(), /* Add expected value here. */);\n"
					  + "\t\t//assertEquals(player.play(2, 3), /* Add expected value here. */);\n";
		
		//Reflection code to test private method.
		try {
		    Method m = TestCaseBuilder.class.getDeclaredMethod("getAssertions", null);
		    m.setAccessible(true);
		    String result = (String) m.invoke(tcb,null);		    
			assertEquals(result, oracle);
		} catch (NoSuchMethodException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	
}
