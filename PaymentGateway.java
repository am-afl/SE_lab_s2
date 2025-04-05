import java.util.*;

interface PaymentGateway {
    Map<String, String> processPayment(Payment payment);
}