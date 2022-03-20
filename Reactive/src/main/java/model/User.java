package model;

import org.bson.Document;

import java.util.Map;

public class User {
    public final String id;
    public final String login;
    public final Currency currency;

    public User(String id, String login, Currency currency) {
        this.id = id;
        this.currency = currency;
        this.login = login;
    }

    public User(Document document) {
        this(
                document.getString("id"),
                document.getString("login"),
                Currency.valueOf(document.getString("currency"))
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + ", login='" + login + '\'' + ", currency='" + currency + '\'' + '}';
    }

    public Document toDocument() {
        return new Document(Map.of("id", id, "login", login, "currency", currency.toString()));
    }
}
