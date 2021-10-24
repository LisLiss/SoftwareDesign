package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class testServlet {
    StringWriter stringWriter;
    PrintWriter printWriter;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private static void makeRequest(String sqlRequest) {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sqlRequest);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Before
    public void initialize() throws IOException {
        MockitoAnnotations.initMocks(this);
        String sqlRequest = "DROP TABLE IF EXISTS PRODUCT;" +
                "CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL);" +
                "INSERT INTO PRODUCT (NAME, PRICE)" +
                "VALUES (\"apple\", 10), (\"pear\", 20), (\"banana\", 30), (\"orange\", 40);";
        makeRequest(sqlRequest);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);
    }

    @Test
    public void addProductServletTest() throws IOException {
        Mockito.when(request.getParameter("name"))
                .thenReturn("mango");
        Mockito.when(request.getParameter("price"))
                .thenReturn("100");
        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("name");
        Mockito.verify(request, Mockito.times(1)).getParameter("price");
        Assert.assertEquals("OK" + System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void getProductServletTest() throws IOException {
        GetProductsServlet getProductServlet = new GetProductsServlet();
        getProductServlet.doGet(request, response);
        String answer = stringWriter.toString();
        String answerExpected = "<html><body>" + System.lineSeparator() +
                "apple\t10</br>" + System.lineSeparator() +
                "pear\t20</br>" + System.lineSeparator() +
                "banana\t30</br>" + System.lineSeparator() +
                "orange\t40</br>" + System.lineSeparator() +
                "</body></html>" + System.lineSeparator();
        Assert.assertEquals(answerExpected, answer);
    }

    @Test
    public void maxQueryTest() throws IOException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("max");
        QueryServlet queryServlet = new QueryServlet();
        queryServlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("command");
        String answer = stringWriter.toString();
        String answerExpected = "<html><body>" + System.lineSeparator() +
                "<h1>Product with max price: </h1>" + System.lineSeparator() +
                "orange\t40</br>" + System.lineSeparator() +
                "</body></html>" + System.lineSeparator();
        Assert.assertEquals(answerExpected, answer);
    }

    @Test
    public void minQueryTest() throws IOException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("min");
        QueryServlet queryServlet = new QueryServlet();
        queryServlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("command");
        String answer = stringWriter.toString();
        String answerExpected = "<html><body>" + System.lineSeparator() +
                "<h1>Product with min price: </h1>" + System.lineSeparator() +
                "apple\t10</br>" + System.lineSeparator() +
                "</body></html>" + System.lineSeparator();
        Assert.assertEquals(answerExpected, answer);
    }

    @Test
    public void sumQueryTest() throws IOException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("sum");
        QueryServlet queryServlet = new QueryServlet();
        queryServlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("command");
        String answer = stringWriter.toString();
        String answerExpected = "<html><body>" + System.lineSeparator() +
                "Summary price: " + System.lineSeparator() +
                "100" + System.lineSeparator() +
                "</body></html>" + System.lineSeparator();
        Assert.assertEquals(answerExpected, answer);
    }

    @Test
    public void countQueryTest() throws IOException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("count");
        QueryServlet queryServlet = new QueryServlet();
        queryServlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("command");
        String answer = stringWriter.toString();
        String answerExpected = "<html><body>" + System.lineSeparator() +
                "Number of products: " + System.lineSeparator() +
                "4" + System.lineSeparator() +
                "</body></html>" + System.lineSeparator();
        Assert.assertEquals(answerExpected, answer);
    }
}