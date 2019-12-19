# Tokenizer-Parser-Evaluator-Interpreter
Tokenizer,Simple Parser, Evaluator, and Interpreter written in Java

Tokenizer.java

Tokenize function that takes a string of expression and returns a series of tokens (such as String Array or ArrayList). Main is written just to test it.

SimpleParser.java

IsExpr function that takes a string of expression and returns true if is syntactically correct and returns if it is not. Uses tokenizer. Main is written just to test it.

Evaluator.java

EvalExpr function that takes a string of expression and returns the values if is syntactically correct and returns null if it is not. Uses tokenizer and SimpleParser. Works for (-,+,/,*) Main is written just to test it.

Interpreter.java

Java program that repeatedly prompts user to enter Python statements and interprets them and display properly to display. The program repeats until the user types ‘quit()’. This program uses evalExpr() from Evaluator.java and handles variables to assign/save and retrieve the saved values from them later. 


[Click for Demo<img src="/Images/video.PNG"></img>](https://drive.google.com/open?id=1BAk3TVng-HSDsmT3GDLiF7wCCf0V-rEi)
