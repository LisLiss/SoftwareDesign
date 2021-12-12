package state;

import token.Token;
import token.Tokenizer;

public interface State {
    State next(Tokenizer tokenizer);

    Token createToken(Tokenizer tokenizer);
}
