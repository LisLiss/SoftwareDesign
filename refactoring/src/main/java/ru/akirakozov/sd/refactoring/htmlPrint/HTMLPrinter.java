package ru.akirakozov.sd.refactoring.htmlPrint;

import ru.akirakozov.sd.refactoring.product.ProductDTO;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HTMLPrinter {
    private final PrintWriter printWriter;

    public HTMLPrinter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void printInfoCommandWithProducts(String command, ArrayList<ProductDTO> products) {
        this.printWriter.println("<html><body>");
        switch (command) {
            case ("max"):
                this.printWriter.println("<h1>Product with max price: </h1>");
                break;
            case ("min"):
                this.printWriter.println("<h1>Product with min price: </h1>");
                break;
        }
        for (ProductDTO product : products) {
            this.printWriter.println(product.getName() + "\t" + product.getPrice() + "</br>");
        }
        this.printWriter.println("</body></html>");
    }

    public void printInfoCommand(String command, int price) {
        this.printWriter.println("<html><body>");
        switch (command) {
            case ("sum"):
                this.printWriter.println("Summary price: ");
                break;
            case ("count"):
                this.printWriter.println("Number of products: ");
                break;
        }
        this.printWriter.println(price);
        this.printWriter.println("</body></html>");
    }

    public void printAllProducts(ArrayList<ProductDTO> products) {
        this.printWriter.println("<html><body>");
        for (ProductDTO product : products) {
            this.printWriter.println(product.getName() + "\t" + product.getPrice() + "</br>");
        }
        this.printWriter.println("</body></html>");
    }
}
