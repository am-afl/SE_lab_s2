import java.util.Map;

/**
 * Abstract base class providing common functionality for payment gateways.
 */
public abstract class BaseGateway implements PaymentGateway {
    /**
     * The API endpoint for the gateway.
     */

    protected String endpoint;

    /**
     * Constructs a BaseGateway with the given endpoint.
     *
     * @param endpoint the API endpoint
     */
    public BaseGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> refundPayment(String transactionId) {
        System.out.println("Refunding transaction: " + transactionId + " at " + endpoint);
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        System.out.println("Checking status for transaction: " + transactionId + " at " + endpoint);
        return "completed";
    }
}