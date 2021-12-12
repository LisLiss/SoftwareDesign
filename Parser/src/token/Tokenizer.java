package token;

import state.EndState;
import state.ErrorState;
import state.StartState;
import state.State;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private State state = new StartState();
    private int ind = 0;
    private String input;

    public char getCurChar() {
        return input.charAt(ind);
    }

    public void next() {
        ind++;
    }

    public boolean isEnd() {
        return ind >= input.length();
    }

    public boolean isDigit() {
        return Character.isDigit(getCurChar());
    }

    public boolean isWhitespace() {
        return Character.isWhitespace(getCurChar());
    }

    public boolean isOperationOrBrace() {
        return "+-/*()".contains(Character.toString(getCurChar()));
    }

    public List<Token> tokenize(String inp) throws ParseException {
        input = inp;
        List<Token> answer = new ArrayList<>();
        state = state.next(this);
        while (!((state instanceof ErrorState) || (state instanceof EndState))) {
            answer.add(state.createToken(this));
            while (!isEnd() && isWhitespace()) {
                next();
            }
            state = state.next(this);
        }
        if (state instanceof ErrorState) {
            throw new ParseException(((ErrorState) state).getMessage(), ind);
        }
        return answer;
    }
}
