package tobyspring.hellospring;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws Exception{
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.perpaer(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
