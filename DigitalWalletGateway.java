import java.util.*;

/**
 * Payment gateway implementation for digital wallets.
 */
class DigitalWalletGateway implements PaymentGateway {
    private String endpoint;

    public DigitalWalletGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Connecting to Digital Wallet API at " + endpoint);
        String transactionId = "DW" + payment.timestamp.getTime();
        System.out.println("Processed digital wallet for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
