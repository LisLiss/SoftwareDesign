package token;

import visitor.TokenVisitor;

public record Brace(TypeOfToken token) implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public TypeOfToken getTypeOfToken() {
        return token;
    }

    @Override
    public String toString() {
        return token.toString();
    }

    @Override
    public int getPriority(){
        return 0;
    }
}
