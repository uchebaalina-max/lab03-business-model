package by.bseu.pp.lab03.model;
public class Product { private final String code; private String name; private double price;
    public Product(String code, String name, double price) {
        if (code == null || code.isBlank()) throw new IllegalArgumentException("Код не может быть пустым");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Имя не может быть пустым");
        if (price < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");

        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) throw new IllegalArgumentException("Новое имя не может быть пустым");
        this.name = newName;
    }

    public void changePrice(double newPrice) {
        if (newPrice < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        this.price = newPrice;
    }

    public double calculateAmount(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Количество должно быть строго больше 0");
        return this.price * quantity;
    }
}