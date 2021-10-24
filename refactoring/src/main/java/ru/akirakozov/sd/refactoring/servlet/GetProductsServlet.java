package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.htmlPrint.HTMLPrinter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
                HTMLPrinter htmlPrinter = new HTMLPrinter(response.getWriter());
                ArrayList<String> names = new ArrayList<>();
                ArrayList<Integer> prices = new ArrayList<>();
                while (rs.next()) {
                    names.add(rs.getString("name"));
                    prices.add(rs.getInt("price"));
                }
                htmlPrinter.printAllProducts(names, prices);
                rs.close();
                stmt.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
