package token;

public class Plus extends Operation{
    @Override
    public Number apply(Number x, Number y) {
        return new Number(x.number() + y.number());
    }

    @Override
    public TypeOfToken getTypeOfToken() {
        return TypeOfToken.PLUS;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String toString() {
        return TypeOfToken.PLUS.toString();
    }
}
