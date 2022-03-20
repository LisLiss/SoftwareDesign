package dao;

import com.mongodb.client.model.Filters;
import com.mongodb.rx.client.MongoCollection;
import model.Currency;
import model.Product;
import model.User;
import org.bson.Document;
import rx.Observable;

public class CatalogDaoImpl implements CatalogDao {

    private final MongoCollection<Document> products;
    private final MongoCollection<Document> users;

    public CatalogDaoImpl(MongoCollection<Document> products, MongoCollection<Document> users) {
        this.products = products;
        this.users = users;
    }

    @Override
    public Observable<User> getUsers() {
        return users.find().toObservable().map(User::new);
    }

    @Override
    public Observable<Product> getProductsById(String id) {
        return users.find(Filters.eq("id", id)).toObservable()
                .map(product -> Currency.valueOf(product.getString("currency")))
                .flatMap(currency -> products.find()
                        .toObservable()
                        .map(prod -> new Product(prod).convert(currency)));
    }

    private Observable<String> tryAdd(Document doc, MongoCollection<Document> mongoDoc) {
        return mongoDoc.find(Filters.eq("id", doc.getString("id"))).toObservable()
                .singleOrDefault(null).flatMap(docum -> {
                    if (docum == null) {
                        return Observable.just("Not added");
                    } else {
                        return mongoDoc.insertOne(doc).asObservable().isEmpty().map(x -> x ? "Not added" : "Added");
                    }
                });
    }

    @Override
    public Observable<String> addUser(User user) {
        return tryAdd(user.toDocument(), users);
    }

    @Override
    public Observable<String> addProduct(Product product) {
        return tryAdd(product.toDocument(), products);
    }
}
