package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.htmlPrint.HTMLPrinter;
import ru.akirakozov.sd.refactoring.product.ProductDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static ru.akirakozov.sd.refactoring.product.ProductDAO.allProducts;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends AbstractServlet {

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLPrinter htmlPrinter = new HTMLPrinter(response.getWriter());
        ArrayList<ProductDTO> allProducts = allProducts();
        htmlPrinter.printAllProducts(allProducts);
    }
}
