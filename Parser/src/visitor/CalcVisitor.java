package visitor;

import token.Brace;
import token.Number;
import token.Operation;
import token.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalcVisitor implements TokenVisitor {
    private Deque<Number> stack = new ArrayDeque<>();

    public int calculate(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        if (stack.size() != 1) {
            throw new IllegalStateException();
        }
        return stack.pop().number();
    }

    @Override
    public void visit(Number number) {
        stack.push(number);
    }

    @Override
    public void visit(Brace brace) {
        throw new IllegalStateException();
    }

    @Override
    public void visit(Operation operation) {
        if (stack.size() < 2) {
            throw new IllegalStateException();
        }
        Number x = stack.pop();
        Number y = stack.pop();
        stack.push(operation.apply(y, x));
    }
}
