package by.bseu.pp.lab03.model;
public class OrderItem { private final Product product; private final int quantity;
    public OrderItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("Товар не может быть null");
        if (quantity <= 0) throw new IllegalArgumentException("Количество должно быть больше 0");

        this.product = product;
        this.quantity = quantity;
    }

    public double getAmount() {
        // Делегируем расчет самому товару (Product уже умеет это делать)
        return product.calculateAmount(quantity);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}