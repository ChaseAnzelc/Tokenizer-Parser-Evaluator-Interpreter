import java.util.ArrayList;
import java.util.List;


public class Tokenizer {
	
	public static List<String> tokenize(String string) {
		
		List<String> tokens = new ArrayList<>();
		
		String token = "";
		
		String regexNumber = "\\d|\\.|\\_";
		String operator = "\\*|\\+|\\=|\\-|\\/|\\%|\\!|\\<|\\>|\\&|\\||\\?|\\:|\\^|\\~";
		String variable = "[a-z]|[A-Z]|\\.|\\_";
		
		
		//letter,number,operator,parenthesis,space
		
		for(int i=0; i< string.length(); i++) {
			String singleChar = string.substring(i, i+1);
			if(singleChar.equals(" ")) {
				if(!token.matches("")) {
					tokens.add(token);
				}
				token = "";
			}
			if(singleChar.matches("\\(|\\)")) {
				
				tokens.add(singleChar);
				token = "";
			}
			else {
				token += singleChar;
				Boolean number = true;
				
	
				
				//Number
				if(token.matches(regexNumber) && number) {
					if(i == string.length()-1) {
						tokens.add(token);
						return tokens;
					}
					for(int j=i+1; j< string.length(); j++) {
						singleChar = string.substring(j, j+1);
						if(!isNumber(singleChar)) {
							tokens.add(token);
							token = "";
							i=j-1;
							break;
						}
						else if(j == string.length()-1) {
							token += string.substring(j, j+1);
							tokens.add(token);
							return tokens;
						}
						token += string.substring(j, j+1);
					}
				}
				
				//operator
				else if(token.matches(operator)) {
					if(i == string.length()-1) {
						tokens.add(token);
						return tokens;
					}
					for(int j=i+1; j< string.length(); j++) {
						singleChar = string.substring(j, j+1);
						if(!singleChar.matches(operator)) {
							tokens.add(token);
							token = "";
							i=j-1;
							break;
						}
						else if(j == string.length()-1) {
							token += string.substring(j, j+1);
							tokens.add(token);
							return tokens;
						}
						token += string.substring(j, j+1);
					}
				}
					
				//variable
				else if(token.matches(variable)) {
					if(i == string.length()-1) {
						tokens.add(token);
						return tokens;
					}
						for(int j=i+1; j< string.length(); j++) {
							singleChar = string.substring(j, j+1);
							if(!(singleChar.matches(variable) || singleChar.matches(regexNumber))) {
								tokens.add(token);
								token = "";
								i=j-1;
								break;
							}
							else if(j == string.length()-1) {
								token += string.substring(j, j+1);
								tokens.add(token);
								return tokens;
							}
							token += string.substring(j, j+1);
						}
					
					
					
					
				}
				token = "";
				
			}
		}
		
		return tokens;
	}
	
private static boolean isNumber(String singleChar) {
		
	String number = "\\d";
	String other = "_|\\.";
	
		
	if(singleChar.matches(number) || singleChar.matches(other)) {
		return true;
	}
		
		
		return false;
	}



	public static void main(String[] args) {
				tokenize("123+56*num1").forEach(System.out::println);
				System.out.println("-------------------------------");
				tokenize("(1+ 23.0) * .9").forEach(System.out::println);
				System.out.println("-------------------------------");
				tokenize("aa1= (14 - 3) *2/a23").forEach(System.out::println);
				System.out.println("-------------------------------");				
				tokenize("(1+ (2 + 1)) * (78+3*15) +45").forEach(System.out::println);
				System.out.println("-------------------------------");
				tokenize("aa1= (14 -3) // 2").forEach(System.out::println);
				System.out.println("-------------------------------");
				tokenize("for i in range(3):").forEach(System.out::println);
				System.out.println("-------------------------------");
				tokenize("2+ 3.25 - 56**a.num .9").forEach(System.out::println);
				System.out.println("-------------------------------");
		
		
	}
	
	
}
