package apps;

import java.util.Stack;

public class SimpleParser {
	public static boolean isExpr(String data) {
		String regexInteger = "\\d+";
		String regexFloat = "\\d+\\.\\d*|\\d*+\\.\\d+";
		String regexSciPostfix = String.format("e[+\\-]?%s", regexInteger);
		String regexNumber = String.format("(%s|%s)(%s)?", regexInteger,regexFloat,regexSciPostfix);
		String regexUnaryOperator = "\\+*|-*";
		String regexBinaryOperator = "(\\+|-)+|\\*{1,2}|\\/{1,2}|%|<|>";
		String regexExtendedNumber = String.format("((%s) *)+\\d+", regexUnaryOperator);
		String regexExpr = String.format("%s|(%s)|(%s|%s) *((%s) *(%s|%s))+", regexNumber, regexExtendedNumber, regexInteger, regexFloat, regexBinaryOperator, regexInteger, regexFloat);
		
		String removeParenthesis = data.replaceAll("[()]", "");
		String removeSpace = removeParenthesis.replaceAll(" ", "");
		return removeSpace.matches(regexExpr) && isBalanced(data);
	}

    public static boolean isBalanced(String s) {
    	
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
            	stack.push('(');

            else if (s.charAt(i) == ')') {
                if (stack.isEmpty())
                	return false;
                if (stack.pop() != '(')
                	return false;
            }
        }
        return stack.isEmpty();
    }
	
	public static void main(String[] args) {	
		System.out.println(isExpr("234"));								// true
		System.out.println(isExpr("1 + 3"));							// true
		System.out.println(isExpr("(1 + 3) * 45"));						// true
		System.out.println(isExpr("(1 + (2 + 1)) * 45"));				// true
		System.out.println(isExpr("(1 + (2 + 1)) * (78+3*15) +45"));	// true
		System.out.println(isExpr("(1 +"));								// false
		System.out.println(isExpr("(1 + 1"));							// false
		System.out.println(isExpr("1 + * 2"));							// false	
	}
}
