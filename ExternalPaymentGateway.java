import java.util.Date;
import java.util.Map;

public class ExternalPaymentGateway implements PaymentGateway {
    private final ConfigManager config;

    public ExternalPaymentGateway(ConfigManager config) {
        this.config = config;
    }

    @Override
    public Map<String, String> processCreditCard(double amount, String currency,
                                                 Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        System.out.println("Connecting to Credit Card API at " + config.get("credit_card_endpoint"));
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public Map<String, String> processDigitalWallet(double amount, String currency,
                                                    Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        System.out.println("Connecting to Digital Wallet API at " + config.get("digital_wallet_endpoint"));
        String transactionId = "DW" + new Date().getTime();
        System.out.println("Processing digital wallet payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public Map<String, String> processBankTransfer(double amount, String currency,
                                                   Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        System.out.println("Connecting to Bank Transfer API at " + config.get("bank_transfer_endpoint"));
        String transactionId = "BT" + new Date().getTime();
        System.out.println("Processing bank transfer payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
