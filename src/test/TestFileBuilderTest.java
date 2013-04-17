package test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import main.*;

public class TestFileBuilderTest {

	private TestFileBuilder tfb;
	
	@Before
	public void setUp() {
	}

	//Test case of a JUnit class containing 1 test case.
	@Test
	public void buildTestTest1(){
		tfb = new TestFileBuilder("Player", "play");
		List<Variable> variables = new LinkedList<Variable>();
		List<String> params = new LinkedList<String>();
		tfb.addTestCase(variables, params);
		
		String result = tfb.buildTest();
		String oracle = "import org.junit.*;\n"
					  + "import static org.junit.Assert.*;\n"
					  + "import org.junit.Before;\n"
					  + "import org.junit.Test;\n\n"
					  + "public class PlayerPlayTest {\n\n"
					  + "\tprivate Player player;\n\n"
					  + "\t@Before\n"
					  + "\tpublic void setUp() throws Exception {\n" 
					  + "\t\tplayer = new Player();\n"
					  + "\t}\n\n"
					  + "\t@Test\n"
					  + "\tpublic void playTest1() {\n"
					  + "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.play(), /* Add expected value here. */);\n"
					  + "\t}\n\n"
					  +"}";
		assertEquals(result,oracle);
	}

	//Test case of a JUnit class containing 2 test cases.
	@Test
	public void buildTestTest2(){
		tfb = new TestFileBuilder("Player", "play");
		List<Variable> variables = new LinkedList<Variable>();
		List<String> params = new LinkedList<String>();
		tfb.addTestCase(variables, params);
		tfb.addTestCase(variables, params);

		String result = tfb.buildTest();
		String oracle = "import org.junit.*;\n"
					  + "import static org.junit.Assert.*;\n"
					  + "import org.junit.Before;\n"
					  + "import org.junit.Test;\n\n"
					  + "public class PlayerPlayTest {\n\n"
					  + "\tprivate Player player;\n\n"
					  + "\t@Before\n"
					  + "\tpublic void setUp() throws Exception {\n" 
					  + "\t\tplayer = new Player();\n"
					  + "\t}\n\n"
					  + "\t@Test\n"
					  + "\tpublic void playTest1() {\n"
					  + "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.play(), /* Add expected value here. */);\n"
					  + "\t}\n\n"
					  + "\t@Test\n"
					  + "\tpublic void playTest2() {\n"
					  + "\t\t//Uncomment or add expected assertions here.\n"
					  + "\t\t//The assertions generated are incomplete as they need the oracle values which must supplied by the user.\n"
					  + "\t\t//assertEquals(player.play(), /* Add expected value here. */);\n"
					  + "\t}\n\n"
					  +"}";
		System.out.println(result);
		System.out.println(oracle);
		assertEquals(result,oracle);
	}

}
