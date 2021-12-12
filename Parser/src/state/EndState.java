package state;

import token.Token;
import token.Tokenizer;

public class EndState implements State {

    @Override
    public State next(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Token createToken(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }
}
