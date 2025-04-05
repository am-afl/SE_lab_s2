import java.util.*;

/**
 * Payment gateway implementation for bank transfers.
 */
class BankTransferGateway implements PaymentGateway {
    private String endpoint;

    public BankTransferGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Connecting to Bank Transfer API at " + endpoint);
        String transactionId = "BT" + payment.timestamp.getTime();
        System.out.println("Processed bank transfer for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}