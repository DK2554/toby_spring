package tobyspring.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    public Payment perpaer(Long orderId, String currency, BigDecimal foreignCurrencyAmount){
        return new Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.perpaer(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }

}
