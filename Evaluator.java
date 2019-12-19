import java.util.List;
import java.util.Stack;

public class Evaluator {

	public static String evalExpr(String expression) 
    { 
		
		boolean isExpr = SimpleParser.isExpr(expression);
		if(!isExpr) {
			return null;
		}
		
		List<String> tokens = Tokenizer.tokenize(expression);
  
        // Stack for Numbers 
        Stack<String> numbers = new Stack<String>(); 
  
        // Stack for Operators 
        Stack<String> operators = new Stack<String>(); 
  
        for (int i = 0; i < tokens.size(); i++) 
        { 
            //Current token is number push to numbers stack
            if (tokens.get(i).matches("\\d++")) {
            	//push number to number stack
                numbers.push(tokens.get(i)); 
            }
  
            // Current token is an opening brace, push it to operators 
            else if (tokens.get(i).equals("(")) {
            	//push opening brace to operators stack
                operators.push(tokens.get(i)); 
            }
            
            // Current token is a closing brace, solve entire brace 
            else if (tokens.get(i).equals(")")) 
            { 
            	//go backwards through braces and calculate
                while (!operators.peek().equals("(")) 
                  numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop())); 
                operators.pop(); 
            } 
  
            // Current token is an Operator 
            else if (tokens.get(i).equals("+") ||tokens.get(i).equals("-") || 
            		tokens.get(i).equals("*") || tokens.get(i).equals("/")) 
            { 
            	//While Operator has precedence, apply Operator to the top 2 elements in Numbers stack 
                while (!operators.empty() && hasPrecedence(tokens.get(i), operators.peek())) 
                  numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop())); 
  
                // Push token to Operators stack
                operators.push(tokens.get(i)); 
            } 
        } 
  
        //Expression has been parsed, finish last Operations
        while (!operators.empty()) 
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop())); 
  
        // Top of Numbers contains the final result
        return numbers.pop(); 
    } 
  
	//Compare operators, if operator 2 is higher or same precedence as operator1, TRUE, else FALSE
    public static boolean hasPrecedence(String operator1, String operator2) 
    { 
        if (operator2.equals("(") || operator2.equals(")")) 
            return false; 
        if ((operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"))) 
            return false; 
        else
            return true; 
    } 
  
    // Operator applied to 2 numbers in numbers stack
    public static String applyOperation(String operator, String b, String a) 
    { 
    	
    	int number1 = Integer.parseInt(a);
    	int number2 = Integer.parseInt(b);
    	
    	
        switch (operator) 
        { 
        case "+": 
            return String.valueOf(number1 + number2); 
        case "-": 
            return String.valueOf(number1 - number2); 
        case "*": 
            return String.valueOf(number1 * number2); 
        case "/": 
            if (b == "0") 
                return null; 
            return String.valueOf(number1 / number2); 
        } 
        return "0"; 
    } 
  
    
    public static void main(String[] args) 
    { 
    	System.out.println(evalExpr("234")); // 234
		System.out.println(evalExpr("1 + 3"));	// 4
		System.out.println(evalExpr("3 - 1"));	//2
		System.out.println(evalExpr("3 - 1 + 5"));	//7
		System.out.println(evalExpr("1 * 3"));	// 3
		System.out.println(evalExpr("6/2 + 2"));	//5
		System.out.println(evalExpr("1 + 2 * 3"));	//7
		System.out.println(evalExpr("(1+3)"));	//4
		System.out.println(evalExpr("4 + 6 + (1 * 3 + 10)"));	// 23
		System.out.println(evalExpr("(1 + (2 + 1)) * 45"));	// 180
		System.out.println(evalExpr("(1 + (2 + 1)) * (78+3*15) +45"));	// 537
		System.out.println(evalExpr("(1 +")); // null
		System.out.println(evalExpr("1 + * 2")); // null	
		System.out.println(evalExpr("10 + 2 * 6")); //22
        System.out.println(evalExpr("100 * 2 + 12")); //212
        System.out.println(evalExpr("100 * (2 + 12)")); //1400
        System.out.println(evalExpr("100 * ( 2+12 ) / 14")); //100
        System.out.println(evalExpr("(1 + (5-2)) * 45"));	// 180
        System.out.println(evalExpr("(1 + 6/2) * 45"));	// 180
    } 
	
	
}
