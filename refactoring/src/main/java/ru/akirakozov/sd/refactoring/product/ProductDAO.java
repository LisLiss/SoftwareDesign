package ru.akirakozov.sd.refactoring.product;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static ru.akirakozov.sd.refactoring.database.SQLDatabase.makeQuery;

public class ProductDAO {

    private static ArrayList<ProductDTO> toProductsDTO(ResultSet resultSet) {
        try {
            ArrayList<ProductDTO> answerProducts = new ArrayList<>();
            while (resultSet.next()) {
                answerProducts.add(new ProductDTO(resultSet.getString("name"), resultSet.getInt("price")));
            }
            return answerProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<ProductDTO> makeCommandReturnArray(String query) {
        ResultSet resultSet = makeQuery(query);
        return toProductsDTO(resultSet);
    }

    private static int makeCommandReturnInt(String query) {
        try (ResultSet resultSet = makeQuery(query)) {
            return resultSet.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ProductDTO> maxProduct() {
        return makeCommandReturnArray("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
    }

    public static ArrayList<ProductDTO> minProduct() {
        return makeCommandReturnArray("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
    }

    public static int sumProducts() {
        return makeCommandReturnInt("SELECT SUM(price) FROM PRODUCT");
    }

    public static int countProducts() {
        return makeCommandReturnInt("SELECT COUNT(*) FROM PRODUCT");
    }

    public static ArrayList<ProductDTO> allProducts() {
        return makeCommandReturnArray("SELECT * FROM PRODUCT");
    }

    public static void addProduct(ProductDTO product) {
        try (Statement statement = DriverManager.getConnection("jdbc:sqlite:test.db").createStatement()) {
            String name = product.getName();
            long price = product.getPrice();
            String query = "INSERT INTO PRODUCT" +
                    "(NAME, PRICE) VALUES" +
                    "(\"" + name + "\"," + price + ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
