package dao;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import org.bson.Document;

public class CatalogDaoConfig {
    public static CatalogDaoImpl getDao() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("online-shop");
        MongoCollection<Document> users = db.getCollection("users");
        MongoCollection<Document> products = db.getCollection("products");

        return new CatalogDaoImpl(users, products);
    }
}