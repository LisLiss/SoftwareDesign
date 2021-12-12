import token.Token;
import token.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Tokenizer tokenizer = new Tokenizer();
        PrintVisitor printVisitor = new PrintVisitor();
        ParseVisitor parseVisitor = new ParseVisitor();
        CalcVisitor calcVisitor = new CalcVisitor();
        List<Token> tokens = tokenizer.tokenize(input);
        System.out.println("PrintVisitor:");
        System.out.println(printVisitor.printPostfix(tokens));
        List<Token> postfixTokens = parseVisitor.toPostfix(tokens);
        System.out.println("ParseVisitor:");
        System.out.println(postfixTokens);
        System.out.println("CalcVisitor:");
        System.out.println(calcVisitor.calculate(postfixTokens));
    }
}
