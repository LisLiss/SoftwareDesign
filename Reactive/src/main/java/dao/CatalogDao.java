package dao;

import model.Product;
import model.User;
import rx.Observable;

public interface CatalogDao {
    Observable<User> getUsers();

    Observable<Product> getProductsById(String id);

    Observable<String> addUser(User user);

    Observable<String> addProduct(Product product);
}
