package token;

public class Div extends Operation {
    @Override
    public Number apply(Number x, Number y) {
        if (y.number() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Number(x.number() / y.number());
    }

    @Override
    public TypeOfToken getTypeOfToken() {
        return TypeOfToken.DIV;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String toString() {
        return TypeOfToken.DIV.toString();
    }
}
