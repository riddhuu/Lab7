import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JFrame {
    private Invoice invoice;
    private JTextArea displayArea;
    private JTextField productNameField, unitPriceField, quantityField;

    public InvoiceGUI() {
        setTitle("Invoice System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize invoice
        Address address = new Address("123 Main St", "Anytown", "State", "12345");
        Customer customer = new Customer("John Doe", address);
        invoice = new Invoice("Sample Invoice", customer);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new AddItemListener());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Create display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        updateDisplay();
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(invoice.getTitle()).append("\n\n");
        sb.append("Customer: ").append(invoice.getCustomer().getName()).append("\n");
        sb.append("Address: ").append(invoice.getCustomer().getAddress()).append("\n\n");
        sb.append("Line Items:\n");
        for (LineItem item : invoice.getLineItems()) {
            sb.append(item.getProduct().getName()).append(" - Quantity: ")
                    .append(item.getQuantity()).append(", Total: $")
                    .append(String.format("%.2f", item.getTotal())).append("\n");
        }
        sb.append("\nTotal Amount Due: $").append(String.format("%.2f", invoice.getTotalAmountDue()));
        displayArea.setText(sb.toString());
    }

    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = productNameField.getText();
                double unitPrice = Double.parseDouble(unitPriceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Product product = new Product(name, unitPrice);
                LineItem item = new LineItem(product, quantity);
                invoice.addLineItem(item);

                updateDisplay();

                // Clear input fields
                productNameField.setText("");
                unitPriceField.setText("");
                quantityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(InvoiceGUI.this, "Invalid input. Please check your entries.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceGUI().setVisible(true));
    }
}