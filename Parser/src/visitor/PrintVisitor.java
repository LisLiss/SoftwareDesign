package visitor;

import token.Brace;
import token.Number;
import token.Operation;
import token.Token;

import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private StringBuilder answer = new StringBuilder();

    public String printPostfix(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        return answer.toString();
    }

    @Override
    public void visit(Number number) {
        answer.append(number.toString()).append(" ");
    }

    @Override
    public void visit(Brace brace) {
        answer.append(brace.toString()).append(" ");
    }

    @Override
    public void visit(Operation operation) {
        answer.append(operation.toString()).append(" ");
    }
}
