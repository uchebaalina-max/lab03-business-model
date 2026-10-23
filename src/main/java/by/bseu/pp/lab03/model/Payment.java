package by.bseu.pp.lab03.model;
import java.time.LocalDate;
public class Payment { private final String paymentId; private final double amount; private final LocalDate date; private PaymentStatus status;
    public Payment(String paymentId, double amount, LocalDate date) {
        if (paymentId == null || paymentId.isBlank()) throw new IllegalArgumentException("ID не может быть пустым");
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть больше 0");
        if (date == null) throw new IllegalArgumentException("Дата не может быть null");

        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.status = PaymentStatus.CREATED;
    }

    // ВОТ ЭТИ ДВА МЕТОДА ТЫ ДОБАВИЛИСЬ
    public String getPaymentId() { return paymentId; }
    public LocalDate getDate() { return date; }

    public void markSuccessful() {
        if (status != PaymentStatus.CREATED) {
            throw new IllegalStateException("Платеж уже обработан");
        }
        this.status = PaymentStatus.SUCCESSFUL;
    }

    public void markFailed() {
        if (status != PaymentStatus.CREATED) {
            throw new IllegalStateException("Платеж уже обработан");
        }
        this.status = PaymentStatus.FAILED;
    }

    public boolean isSuccessful() {
        return status == PaymentStatus.SUCCESSFUL;
    }

    public double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
}