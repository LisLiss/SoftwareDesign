package model;

public enum Currency {
    USD, EUR, RUB;
    private final int USD_TO_RUB = 100;
    private final int EUR_TO_RUB = 110;

    private double price(Currency currency) {
        if (currency == USD) {
            return 1.0 / USD_TO_RUB;
        }
        if (currency == EUR) {
            return 1.0 / EUR_TO_RUB;
        }
        return 1;
    }

    public double converter(Currency currency) {
        return price(currency) / price(this);
    }
}
