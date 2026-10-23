package by.bseu.pp.lab03.model;
public class Customer { private final String code; private String name; private boolean active; private double creditLimit;
    public Customer(String code, String name, double creditLimit) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Код не может быть пустым");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным");
        }

        this.code = code;
        this.name = name;
        this.creditLimit = creditLimit;
        this.active = true;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public double getCreditLimit() { return creditLimit; }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Новое имя не может быть пустым");
        }
        this.name = newName;
    }

    public void changeCreditLimit(double newCreditLimit) {
        if (newCreditLimit < 0) {
            throw new IllegalArgumentException("Новый лимит не может быть отрицательным");
        }
        this.creditLimit = newCreditLimit;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean canPlaceOrder(double orderTotal) {
        if (orderTotal < 0) {
            throw new IllegalArgumentException("Сумма заказа не может быть отрицательной");
        }
        return this.active && orderTotal <= this.creditLimit;
    }
}