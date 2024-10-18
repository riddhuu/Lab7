public class LineItem {
    private Product product;
    private int quantity;
    private double total;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        calculateTotal();
    }

    private void calculateTotal() {
        this.total = product.getUnitPrice() * quantity;
    }

    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }
}