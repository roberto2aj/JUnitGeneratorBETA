package test;

import static org.junit.Assert.*;

import main.Constants;

import org.junit.Before;
import org.junit.Test;

public class ConstantsTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void firstCharToLowerCaseTest(){
		assertEquals(Constants.firstCharToLowerCase(null), null);
		assertEquals(Constants.firstCharToLowerCase(""), "");
		assertEquals(Constants.firstCharToLowerCase("a"), "a");
		assertEquals(Constants.firstCharToLowerCase("A"), "a");
		assertEquals(Constants.firstCharToLowerCase("Aa"), "aa");
		assertEquals(Constants.firstCharToLowerCase("aa"), "aa");
	}

	@Test
	public void firstCharToUpperCaseTest(){
		assertEquals(Constants.firstCharToUpperCase(null), null);
		assertEquals(Constants.firstCharToUpperCase(""), "");
		assertEquals(Constants.firstCharToUpperCase("a"), "A");
		assertEquals(Constants.firstCharToUpperCase("A"), "A");
		assertEquals(Constants.firstCharToUpperCase("Aa"), "Aa");
		assertEquals(Constants.firstCharToUpperCase("aa"), "Aa");
	}

}
