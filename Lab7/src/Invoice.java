import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String title;
    private Customer customer;
    private List<LineItem> lineItems;
    private double totalAmountDue;

    public Invoice(String title, Customer customer) {
        this.title = title;
        this.customer = customer;
        this.lineItems = new ArrayList<>();
        this.totalAmountDue = 0.0;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        calculateTotalAmountDue();
    }

    private void calculateTotalAmountDue() {
        totalAmountDue = lineItems.stream().mapToDouble(LineItem::getTotal).sum();
    }

    // Getters and setters
    public String getTitle() { return title; }
    public Customer getCustomer() { return customer; }
    public List<LineItem> getLineItems() { return lineItems; }
    public double getTotalAmountDue() { return totalAmountDue; }
}