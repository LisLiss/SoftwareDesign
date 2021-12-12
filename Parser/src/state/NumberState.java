package state;

import token.Number;
import token.Token;
import token.Tokenizer;

public class NumberState implements State{
    @Override
    public State next(Tokenizer tokenizer) {
        if (tokenizer.isEnd()) {
            return new EndState();
        }
        if (tokenizer.isOperationOrBrace()) {
            return new StartState();
        }
        return new ErrorState("Unexpected character " + tokenizer.getCurChar());

    }

    @Override
    public Token createToken(Tokenizer tokenizer) {
        String number = "";
        while (!tokenizer.isEnd() && tokenizer.isDigit()){
            number += tokenizer.getCurChar();
            tokenizer.next();
        }
        return new Number(Integer.parseInt(number));
    }
}
