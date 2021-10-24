package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.htmlPrint.HTMLPrinter;
import ru.akirakozov.sd.refactoring.product.ProductDAO;
import ru.akirakozov.sd.refactoring.product.ProductDTO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static ru.akirakozov.sd.refactoring.product.ProductDAO.allProducts;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLPrinter htmlPrinter = new HTMLPrinter(response.getWriter());
        ArrayList<ProductDTO> allProducts = allProducts();
        htmlPrinter.printAllProducts(allProducts);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
