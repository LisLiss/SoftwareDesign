package state;

import token.*;

public class StartState implements State {
    @Override
    public State next(Tokenizer tokenizer) {
        if (tokenizer.isEnd()) {
            return new EndState();
        }
        if (tokenizer.isOperationOrBrace()) {
            return new StartState();
        }
        if (tokenizer.isDigit()) {
            return new NumberState();
        }
        return new ErrorState("Unexpected character " + tokenizer.getCurChar());
    }

    @Override
    public Token createToken(Tokenizer tokenizer) {
        char c = tokenizer.getCurChar();
        tokenizer.next();
        switch (c) {
            case '+':
                return new Plus();
            case '-':
                return new Minus();
            case '*':
                return new Mult();
            case '/':
                return new Div();
            case '(':
                return new Brace(TypeOfToken.LEFT);
            case ')':
                return new Brace(TypeOfToken.RIGHT);
            default:
                return null;
        }
    }
}
