import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArithTest {

	
	@Test 
	public void validatePrefixOrderTest(){
		
		String[] input = { "*", "-", "1", "2", "3" };
		assertEquals(true, Arith.validatePrefixOrder(input));
		String[] s = { "+", "*", "-", "1", "2","3", "-", "10", "+", "3", "/", "6", "3" };
		assertEquals(true, Arith.validatePrefixOrder(s));	
		String[] s1 = { "*", "-", "1", "2","3", "-", "10", "+", "3", "/", "6", "3" };
		assertEquals(false, Arith.validatePrefixOrder(s1));
		String[] s2 = {"+" , "3", "+"};
		assertEquals(false, Arith.validatePrefixOrder(s2));	
		String[] s3 = {"+"};
		assertEquals(false, Arith.validatePrefixOrder(s3));	
	}
	
	
	
	
	@Test 
	public void validatePostfixOrderTest(){
		
		String[] s ={"1","2","3","+","*","4","/","5","-"};
		assertEquals(true,Arith.validatePostfixOrder(s));
		String[] s2 = {"+","3","+"};
		assertEquals(false, Arith.validatePostfixOrder(s2));
		String[] input = { "*", "-", "1", "2", "3" };
		assertEquals(false, Arith.validatePostfixOrder(input));

	}
	
	
	@Test
	public void evaluatePrefixOrderTest(){
		
		String[] s = { "-", "/", "*", "1", "+","2","3","4","5" };
		assertEquals(-4, Arith.evaluatePrefixOrder(s));
		
	}
	
	
	
	
	
	@Test
	public void evaluatePostfixOrderTest(){
			
		String[] s = {"1","2","3","+","*","4","/","5","-"};
		assertEquals(-4, Arith.evaluatePostfixOrder(s));
	}
	
	
	@Test
	public void convertPostfixToInfixTest() {

		String[] s = {"1", "2", "-", "3", "*"};
		String[] news = Arith.convertPostfixToInfix(s);
		String [] t = {"(","(","1","-","2",")","*","3",")"};
		assertArrayEquals(t, news);
	}
	
	
	
	
	
	@Test
	public void  convertPrefixToPostfixTester(){
		
		
		String[] input = { "*", "-", "1", "2", "3" };
		String test[] = Arith.convertPrefixToPostfix(input);
		String t = Arrays.toString(test);
		String[] testoutput = { "1", "2", "-", "3", "*" };
	String tout = Arrays.toString(testoutput);
		assertEquals(tout, t);
		
	}
	
	@Test
	public void convertPostfixToPrefixTest(){
		
		String[] input = { "1","2","-","3","*" };
		String[] test = Arith.convertPostfixToPrefix(input);
		String t = Arrays.toString(test);
		String[] testOut = { "*","-","1","2","3"};
		String tout = Arrays.toString(testOut);
		assertEquals(tout,t);
	}
	
	
	@Test
	public void convertPrefixToInfixTest(){
		String[] input = { "*", "-", "1", "2", "3" };
		String[] news = Arith.convertPrefixToInfix(input);
		String[] out = { "(","(", "1", "-", "2",")", "*", "3",")" };
		assertArrayEquals(out, news);
	}
}
