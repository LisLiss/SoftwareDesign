package token;

public class Mult extends Operation{
    @Override
    public Number apply(Number x, Number y) {
        return new Number(x.number() * y.number());
    }

    @Override
    public TypeOfToken getTypeOfToken() {
        return TypeOfToken.MULT;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String toString() {
        return TypeOfToken.MULT.toString();
    }
}
