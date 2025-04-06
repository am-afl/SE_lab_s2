import java.util.Date;
import java.util.Map;

/**
 * Payment gateway implementation for Stripe.
 */
public class StripeGateway extends BaseGateway {
    /**
     * Constructs a StripeGateway with the given endpoint.
     *
     * @param endpoint the Stripe API endpoint
     */
    public StripeGateway(String endpoint) {
        super(endpoint);
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        System.out.println("Processing Stripe payment for " + customerInfo.get("name") + " at " + endpoint);
        String transactionId = "STRIPE" + new Date().getTime();
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
