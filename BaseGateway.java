import java.util.Map;

public abstract class BaseGateway implements PaymentGateway {
    protected String endpoint;

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