package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.product.ProductDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.product.ProductDAO.addProduct;

/**
 * @author akirakozov
 */
public class AddProductServlet extends AbstractServlet {

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        addProduct(new ProductDTO(name, price));

        response.getWriter().println("OK");
    }
}
