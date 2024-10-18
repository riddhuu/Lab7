public class Product {
    private String name;
    private double unitPrice;

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    // Getters
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
}