package ru.akirakozov.sd.refactoring.product;

public class ProductDTO {
    private final String name;
    private final long price;

    public ProductDTO(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
