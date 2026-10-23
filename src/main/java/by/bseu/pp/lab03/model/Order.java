package by.bseu.pp.lab03.model;
import java.util.ArrayList; import java.util.List;
public class Order { private final Customer customer; private final List<OrderItem> items; private OrderStatus status; private Payment payment;
    public Order(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Клиент не может быть null");
        }
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
        this.payment = null;
    }

    // Геттеры
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public int getItemsCount() { return items.size(); }
    public OrderStatus getStatus() { return status; }
    public Payment getPayment() { return payment; }

    public void addItem(Product product, int quantity) {
        // Добавлять товары можно ТОЛЬКО в статусе CREATED
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Нельзя добавить товар в заказ со статусом " + status);
        }
        // Валидация product и quantity выполняется внутри конструктора OrderItem
        this.items.add(new OrderItem(product, quantity));
    }

    public double total() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getAmount();
        }
        return sum;
    }

    public void confirm() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Подтвердить можно только созданный заказ");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Нельзя подтвердить пустой заказ");
        }
        if (!customer.isActive()) {
            throw new IllegalStateException("Клиент неактивен");
        }
        if (!customer.canPlaceOrder(this.total())) {
            throw new IllegalStateException("Сумма заказа превышает кредитный лимит");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        // Отменить можно только CREATED или CONFIRMED.
        // Оплаченный (PAID) или уже отмененный (CANCELLED) - нельзя.
        if (status == OrderStatus.PAID || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Нельзя отменить оплаченный или уже отмененный заказ");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void pay(Payment payment) {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Оплатить можно только подтвержденный заказ");
        }
        if (payment == null) {
            throw new IllegalArgumentException("Платеж не может быть null");
        }
        if (!payment.isSuccessful()) {
            // Ошибка состояния, так как данные верные, но статус платежа не позволяет провести оплату
            throw new IllegalStateException("Платеж не был успешным");
        }
        if (payment.getAmount() != this.total()) {
            throw new IllegalArgumentException("Сумма платежа не совпадает с суммой заказа");
        }

        this.payment = payment;
        this.status = OrderStatus.PAID;
    }

    public boolean isPaid() {
        return status == OrderStatus.PAID;
    }
}