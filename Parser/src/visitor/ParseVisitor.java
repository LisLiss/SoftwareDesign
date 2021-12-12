package visitor;

import token.*;
import token.Number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParseVisitor implements TokenVisitor {
    private List<Token> postfix = new ArrayList<>();
    private Deque<Token> stack = new ArrayDeque<>();

    public List<Token> toPostfix(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
        return postfix;
    }

    @Override
    public void visit(Number number) {
        postfix.add(number);
    }

    @Override
    public void visit(Brace brace) {
        if (brace.getTypeOfToken() == TypeOfToken.LEFT) {
            stack.push(brace);
        } else {
            if (!stack.isEmpty()) {
                Token tmpToken = stack.pop();
                while (!stack.isEmpty() && tmpToken.getTypeOfToken() != TypeOfToken.LEFT) {
                    postfix.add(tmpToken);
                    tmpToken = stack.pop();
                }
                if (tmpToken.getTypeOfToken() != TypeOfToken.LEFT) {
                    throw new IllegalStateException();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void visit(Operation operation) {
        if (!stack.isEmpty()) {
            Token tmpToken = stack.peek();
            while (!stack.isEmpty() && operation.getPriority() <= tmpToken.getPriority()) {
                postfix.add(stack.pop());
                tmpToken = stack.peek();
            }
        }
        stack.push(operation);
    }
}
