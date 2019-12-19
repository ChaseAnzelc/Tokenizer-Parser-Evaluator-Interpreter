import java.util.Scanner;
import java.util.HashMap; 
import java.util.Map;
import java.util.Map.Entry; 

public class Interpreter {

	static HashMap<String, String> variables = new HashMap<>(); 
	
    public static void variables(String userInput) 
    { 
		if (userInput.contains("="))
		{
			String variable = userInput.substring( 0, userInput.indexOf("=")).replaceAll(" ", "");
			

			
			String value = Evaluator.evalExpr(userInput.substring(userInput.indexOf("=") + 1, userInput.length()));
			if (value == null)
			{
				String newValue = userInput.substring(userInput.indexOf("=") + 1, userInput.length());
				for (Entry<String, String> entry : variables.entrySet())
				{
				    if (newValue.contains(entry.getKey()))
		    		{
				    	String newValue2 = newValue.replaceAll(entry.getKey(), entry.getValue());
				    	
				    	for (Entry<String, String> entry1 : variables.entrySet()) {
						    if (newValue2.contains(entry1.getKey()))
				    		{
						    	newValue2 = newValue2.replaceAll(entry1.getKey(), entry1.getValue());
				    		}
						}
				    	
				    	variables.put(variable, Evaluator.evalExpr(newValue2)); 
		    		}
				}
			}
			else
			{
				variables.put(variable, value); 
			}						
		}
		else
		{
	    	String output = userInput;

			for (Entry<String, String> entry : variables.entrySet()) {
			    if (output.contains(entry.getKey()))
	    		{
			    	output = output.replaceAll(entry.getKey(), entry.getValue());
	    		}
			}
			System.out.println(Evaluator.evalExpr(output));
		}	    
    }
    
	private static void Interpret(String userInput)
	{	
		String expr = Evaluator.evalExpr(userInput);		
		
		if(expr == null)
		{
			variables(userInput);
		}
		else
		{
			System.out.println(Evaluator.evalExpr(userInput));
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String userInput;
		while(true)
		{
			System.out.print(">>> ");
			userInput = scan.nextLine().trim();
			if(userInput.equals(""))
				continue;
			else if(userInput.equals("quit()"))
					break;
			else
				Interpret(userInput);
		}
		scan.close();
	}
}