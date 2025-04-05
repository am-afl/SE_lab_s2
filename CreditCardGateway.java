import java.util.*;

class CreditCardGateway implements PaymentGateway {
    private String endpoint;

    public CreditCardGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Connecting to Credit Card API at " + endpoint);
        String transactionId = "CC" + payment.timestamp.getTime();
        System.out.println("Processed credit card for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}