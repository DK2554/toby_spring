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
     private ExRateProvider exRateProvider;

     public PaymentService(){
         this.exRateProvider = new SimpleExRateService();

     }    public Payment perpaer(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        WebApiExRateProvider exRateProvider = new WebApiExRateProvider();
        BigDecimal exRate = exRateProvider.getExRate(currency);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

 }
