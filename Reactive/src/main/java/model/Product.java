package model;

import org.bson.Document;

import java.util.Map;

public class Product {
    public final String id;
    public final String name;
    public final Double price;
    public final Currency currency;

    public Product(String id, String name, Double price, Currency currency) {
        this.id = id;
        this.currency = currency;
        this.name = name;
        this.price = price;
    }

    public Product(Document document) {
        this(
                document.getString("id"),
                document.getString("name"),
                document.getDouble("price"),
                Currency.valueOf(document.getString("currency"))
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + ", name='" + name + ", price='" + price + '\'' + ", currency='" + currency + '\'' + '}';
    }

    public Document toDocument() {
        return new Document(Map.of("id", id, "name", name, "price", price, "currency", currency.toString()));
    }

    public Product convert(Currency currency) {
        return new Product(this.id, this.name, this.price * this.currency.converter(currency), currency);
    }
}
