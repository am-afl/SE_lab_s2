import java.util.Map;

public interface PaymentGateway {
    Map<String, String> processCreditCard(double amount, String currency,
                                          Map<String, String> customerInfo, Map<String, String> paymentDetails);

    Map<String, String> processDigitalWallet(double amount, String currency,
                                             Map<String, String> customerInfo, Map<String, String> paymentDetails);

    Map<String, String> processBankTransfer(double amount, String currency,
                                            Map<String, String> customerInfo, Map<String, String> paymentDetails);
}
