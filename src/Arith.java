// -------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations for
 * prefix and postfix arithmetic expressions.
 *
 * @author Michael Cullen
 * @version 1/12/15 13:03:48
 */

public class Arith {

	// ~ Validation methods
	// ..........................................................

	/**
	 * Validation method for prefix notation.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals hopefully in prefix
	 *            order. The method assumes that each of these literals can be
	 *            one of: - "+", "-", "*", or "/" - or a valid string
	 *            representation of an integer.
	 *
	 * @return true if the parameter is indeed in prefix notation, and false
	 *         otherwise.
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[]) {
		int counter = 1;

		for (int i = 0; i < prefixLiterals.length; i++) {
			try {
				Integer.parseInt(prefixLiterals[i]);
				counter--;
			} catch (NumberFormatException e) {
				counter++;
			}
			if ((counter <= 0) && (i != prefixLiterals.length - 1)) {
				return false;
			}
		}
		if(counter <= 0){
		return true;
		}
		return false;
	
	}

	/**
	 * Validation method for postfix notation.
	 *
	 * @param postfixLiterals
	 *            : an array containing the string literals hopefully in postfix
	 *            order. The method assumes that each of these literals can be
	 *            one of: - "+", "-", "*", or "/" - or a valid string
	 *            representation of an integer.
	 *
	 * @return true if the parameter is indeed in postfix notation, and false
	 *         otherwise.
	 **/
	public static boolean validatePostfixOrder(String postfixLiterals[]) {
		int counter = 1;
		for (int i = postfixLiterals.length - 1; i >= 0; i--) {
			try {
				Integer.parseInt(postfixLiterals[i]);
				counter--;
			} catch (NumberFormatException e) {
				counter++;
			}
			if ((counter < 0) && (i != postfixLiterals.length - 1)) {
				return false;
			}
		}
		if(counter == 0){
			return true;
		}
		return false;
	}

	// ~ Evaluation methods
	// ..........................................................

	/**
	 * Evaluation method for prefix notation.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: -
	 *            "+", "-", "*", or "/" - or a valid string representation of an
	 *            integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/

	public static int evaluatePrefixOrder(String prefixLiterals[]) {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		int tally = 0;
		int one = 0;
		int two = 0;
		boolean twoNum = false;
		String c;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {

			c = prefixLiterals[i];
			if ((c.equals("+")) || (c.equals("-")) || (c.equals("*")) || (c.equals("/"))) {
				one = Integer.parseInt(list.pop());
				two = Integer.parseInt(list.pop());
				if (prefixLiterals[i].equals("+")) {
					tally = one + two;
				} else if (prefixLiterals[i].equals("*")) {
					tally = one * two;
				} else if (prefixLiterals[i].equals("-")) {
					tally = one - two;
				} else if (prefixLiterals[i].equals("/")) {
					tally = one / two;
				}
				list.push(Integer.toString(tally));
			} else {
				list.push(c);
			}
		}
		return Integer.parseInt(list.pop());
	}

	/**
	 * Evaluation method for postfix notation.
	 *
	 * @param postfixLiterals
	 *            : an array containing the string literals in postfix order.
	 *            The method assumes that each of these literals can be one of:
	 *            - "+", "-", "*", or "/" - or a valid string representation of
	 *            an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/

	public static int evaluatePostfixOrder(String postfixLiterals[]) {
		int tally = 0;
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

		for (int i = 0; i < postfixLiterals.length; i++) {

			try {
				int value = Integer.parseInt(postfixLiterals[i]);
				testDLL.push(value);
			} catch (NumberFormatException e) {
				int two = testDLL.pop();
				int one = testDLL.pop();
				if (postfixLiterals[i].equals("+")) {
					tally = one + two;
				} else if (postfixLiterals[i].equals("*")) {
					tally = one * two;
				} else if (postfixLiterals[i].equals("-")) {
					tally = one - two;
				} else if (postfixLiterals[i].equals("/")) {
					tally = one / two;
				}
				testDLL.push(tally);
			}

		}

		return tally;

	}

	// ~ Conversion methods
	// ..........................................................

	/**
	 * Converts prefix to postfix.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: -
	 *            "+", "-", "*", or "/" - or a valid string representation of an
	 *            integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertPrefixToPostfix(String prefixLiterals[]) {

		DoublyLinkedList<String> f = new DoublyLinkedList<String>();
		DoublyLinkedList<String> s = new DoublyLinkedList<String>();
		String[] val = new String[prefixLiterals.length];
		int c = 0;

		for (int i = 0; i < prefixLiterals.length; i++) {
			String ch = prefixLiterals[i];
			if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")) {
				s.push(ch);
				f.push("0");
			} else {
				val[c] = ch;
				c++;
				if (f.head != null) {
					while (f.head.data.equals("1")) {
						val[c] = s.pop();
						c++;
						f.pop();
						if (f.isEmpty())
							break;
					}
					if (!f.isEmpty())
						f.pop();
					f.push("1");
				}
			}
		}
		return val;
	}

	/**
	 * Converts postfix to prefix.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in postfix order.
	 *            The method assumes that each of these literals can be one of:
	 *            - "+", "-", "*", or "/" - or a valid string representation of
	 *            an integer.
	 *
	 * @return the expression in prefix order.
	 **/
	public static String[] convertPostfixToPrefix(String postfixLiterals[]) {
		DoublyLinkedList<String> s = new DoublyLinkedList<String>();
		String s2 = "";
		String s1 = "";

		for (int i = 0; i < postfixLiterals.length; i++) {
			String ch = postfixLiterals[i];
			if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")) {
				s1 = s.pop();
				s2 = s.pop();
				s.enqueue(ch + "(" + s2 + ")" + "(" + s1 + ")");
			} else {
				s.push(ch);
			}
		}
		s1 = s.pop();
		String str;
		String[] arr = new String[postfixLiterals.length];
		String num = "";
		int in = 0;

		for (int i = 0; i < s1.length(); i++) {
			str = String.valueOf(s1.charAt(i));
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
				arr[in] = str;
				in++;
			} else {
				if (str.equals(")")) {
					if(num.length() != 0){
					arr[in] = num;
					num = "";
					in++;
					}
				} else if (str.equals("(")) {
				} else {
					num = num + str;
				}
			}
		}
		return arr;
	}

	/**
	 * Converts prefix to infix.
	 *
	 * @param infixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: -
	 *            "+", "-", "*", or "/" - or a valid string representation of an
	 *            integer.
	 *
	 * @return the expression in infix order.
	 **/

	public static String[] convertPrefixToInfix(String prefixLiterals[]) {

		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String s;
		String s1;
		String s2;
		int count = 0;

		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			s = prefixLiterals[i];
			if ((s.equals("+")) || (s.equals("-")) || (s.equals("*")) || (s.equals("/"))) {

				s1 = list.pop();
				s2 = list.pop();
				list.push("(" + "." + s1 + "!" + s + "." + s2 + "! " + ")");
				count++;

			} else {
				list.push(s);
			}
		}
		s = list.pop();
		count = 2 * count;
		String str;
		String[] arr = new String[prefixLiterals.length + count];
		String num = "";
		int in = 0;

		for (int i = 0; i < s.length(); i++) {
			str = String.valueOf(s.charAt(i));
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || (str.equals("("))
					|| (str.equals(")"))) {
				arr[in] = str;
				in++;
			} else if (str.equals("!")) {
				if ((num.length() != 0)) {
					arr[in] = num;
					num = "";
					str = "";
					in++;
				}
			} else if (str.equals(".")) {
			} else {
				if (!str.equals(" ")) {
					num = num + str;
				}
			}
		}
		return arr;

	}

	/**
	 * Converts postfix to infix.
	 *
	 * @param infixLiterals
	 *            : an array containing the string literals in postfix order.
	 *            The method assumes that each of these literals can be one of:
	 *            - "+", "-", "*", or "/" - or a valid string representation of
	 *            an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[]) {

		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		int count = 0;

		for (int i = 0; i < postfixLiterals.length; i++) {
			String c = postfixLiterals[i];
			if ((c.equals("+")) || (c.equals("-")) || (c.equals("*")) || (c.equals("/"))) {
				String b = list.pop();
				String a = list.pop();
				list.push("(" + "." + a + "!" + c + "." + b + "!" + ")");
				count++;
			} else
				list.push(c);
		}
		String s = list.pop();
		count = 2 * count;
		String str;
		String[] arr = new String[postfixLiterals.length + count];
		String num = "";
		int in = 0;

		for (int i = 0; i < s.length(); i++) {
			str = String.valueOf(s.charAt(i));
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || (str.equals("("))
					|| (str.equals(")"))) {
				arr[in] = str;
				in++;
			} else if (str.equals("!")) {
				if ((num.length() != 0)) {
					arr[in] = num;
					num = "";
					str = "";
					in++;
				}
			} else if (str.equals(".")) {
			} else {
				if (!str.equals(" ")) {
					num = num + str;
				}
			}
		}
		return arr;
	}
}
