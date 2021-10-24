package ru.akirakozov.sd.refactoring.htmlPrint;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HTMLPrinter {
    private final PrintWriter printWriter;

    public HTMLPrinter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void printInfoCommandWithProduct(String command, String name, int price) {
        this.printWriter.println("<html><body>");
        switch (command) {
            case ("max"):
                this.printWriter.println("<h1>Product with max price: </h1>");
                break;
            case ("min"):
                this.printWriter.println("<h1>Product with min price: </h1>");
                break;
        }
        this.printWriter.println(name + "\t" + price + "</br>");
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

    public void printAllProducts(ArrayList<String> names, ArrayList<Integer> prices) {
        this.printWriter.println("<html><body>");
        for (int i = 0; i < names.size(); i++) {
            this.printWriter.println(names.get(i) + "\t" + prices.get(i) + "</br>");
        }
        this.printWriter.println("</body></html>");
    }
}
