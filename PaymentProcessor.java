import java.util.Map;

public class PaymentProcessor {
    private PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public Map<String, String> process(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        if (!validate(amount, currency, customerInfo)) {
            return Map.of("status", "failed", "message", "Validation failed");
        }

        Map<String, String> result = gateway.processPayment(amount, currency, customerInfo, paymentDetails);
        logTransaction(amount, currency, customerInfo, result);
        return result;
    }

    private boolean validate(double amount, String currency, Map<String, String> customerInfo) {
        return amount > 0 && (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP"))
                && customerInfo.containsKey("email");
    }

    private void logTransaction(double amount, String currency, Map<String, String> customerInfo, Map<String, String> result) {
        System.out.println(String.format("Transaction Log: %.2f %s for %s - %s", amount, currency, customerInfo.get("name"), result));
    }
}