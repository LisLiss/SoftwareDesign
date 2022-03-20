import dao.CatalogDaoConfig;
import io.reactivex.netty.protocol.http.server.HttpServer;
import httpServer.HttpHandler;
import httpServer.HttpHandlerImpl;
import rx.Observable;

public class Main {
    public static void main(String[] args) {
        HttpHandler client = new HttpHandlerImpl(CatalogDaoConfig.getDao());
        HttpServer.newServer(8080)
                .start((request, response) -> {
                    Observable<String> r = client.makeRequest(request);
                    return response.writeString(r);
                }).awaitShutdown();
    }
}