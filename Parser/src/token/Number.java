package token;

import visitor.TokenVisitor;

public record Number(int number) implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public TypeOfToken getTypeOfToken() {
        return TypeOfToken.NUMBER;
    }

    @Override
    public String toString() {
        return "NUMBER(" + number + ")";
    }

    public int getPriority(){
        return 3;
    }
}
