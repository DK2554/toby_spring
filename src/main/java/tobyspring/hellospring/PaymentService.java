package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
    public Payment perpaer(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/USD"+currency);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String collect = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data =mapper.readValue(collect, ExRateData.class);
        BigDecimal exRate = data.rates().get("KRW");
        System.out.println(exRate);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
    public static void main(String[] args) throws Exception{
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.perpaer(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }

}
