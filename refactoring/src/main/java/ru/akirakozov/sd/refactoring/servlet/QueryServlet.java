package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.htmlPrint.HTMLPrinter;
import ru.akirakozov.sd.refactoring.product.ProductDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static ru.akirakozov.sd.refactoring.product.ProductDAO.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends AbstractServlet {
    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        HTMLPrinter htmlPrinter = new HTMLPrinter(response.getWriter());
        switch (command) {
            case "max":
                ArrayList<ProductDTO> productMax = maxProduct();
                htmlPrinter.printInfoCommandWithProducts("max", productMax);
                break;
            case "min":
                ArrayList<ProductDTO> productMin = minProduct();
                htmlPrinter.printInfoCommandWithProducts("min", productMin);
                break;
            case "sum":
                htmlPrinter.printInfoCommand("sum", sumProducts());
                break;
            case "count":
                htmlPrinter.printInfoCommand("count", countProducts());
                break;
            default:
                response.getWriter().println("Unknown command: " + command);
        }
    }

}
