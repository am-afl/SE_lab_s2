import java.util.Date;
import java.util.Map;

public class PayPalGateway extends BaseGateway {
    public PayPalGateway(String endpoint) {
        super(endpoint);
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        System.out.println("Processing PayPal payment for " + customerInfo.get("name") + " at " + endpoint);
        String transactionId = "PAYPAL" + new Date().getTime();
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}