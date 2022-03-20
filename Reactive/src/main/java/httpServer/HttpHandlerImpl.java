package httpServer;

import dao.CatalogDao;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import model.Currency;
import model.Product;
import model.User;
import rx.Observable;

import java.util.Objects;

public class HttpHandlerImpl implements HttpHandler {
    private final CatalogDao dao;

    public HttpHandlerImpl(CatalogDao dao) {
        this.dao = dao;
    }

    @Override
    public Observable<String> makeRequest(HttpServerRequest<?> req) {
        String request = req.getDecodedPath().substring(1);
        switch (request) {
            case "user_add":
                return this.userAdd(req);
            case "product_add":
                return this.productAdd(req);
            case "user_get":
                return this.userGet();
            case "product_get":
                return this.productGet(req);
            default:
                return Observable.just("Bug");
        }
    }

    private String getParam(HttpServerRequest<?> req, String param) {
        return req.getQueryParameters().get(param).get(0);
    }

    private Observable<String> userGet() {
        return this.dao.getUsers().map(Objects::toString);
    }

    private Observable<String> productGet(HttpServerRequest<?> req) {
        return this.dao.getProductsById(this.getParam(req, "id")).map(Objects::toString);
    }

    private Observable<String> userAdd(HttpServerRequest<?> req) {
        User user = new User(this.getParam(req, "id"), this.getParam(req, "login"),
                Currency.valueOf(this.getParam(req, "currency")));

        return this.dao.addUser(user).map(Objects::toString);
    }

    private Observable<String> productAdd(HttpServerRequest<?> req) {
        Product product = new Product(this.getParam(req, "id"), this.getParam(req, "name"),
                Double.parseDouble(this.getParam(req, "price")),
                Currency.valueOf(this.getParam(req, "currency")));

        return this.dao.addProduct(product).map(Objects::toString);
    }
}
