import java.util.Map;

/**
 * Handles payment processing using a provided payment gateway.
 */
public class PaymentProcessor {
    /**
     * The payment gateway used to process transactions.
     */

    private PaymentGateway gateway;

    /**
     * Constructs a PaymentProcessor with the given gateway.
     *
     * @param gateway the payment gateway
     */
    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Processes a payment.
     *
     * @param amount         the amount to be paid
     * @param currency       the currency
     * @param customerInfo   customer details
     * @param paymentDetails payment method details
     * @return a map containing the result of the payment
     */
    public Map<String, String> process(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        if (!validate(amount, currency, customerInfo)) {
            return Map.of("status", "failed", "message", "Validation failed");
        }

        Map<String, String> result = gateway.processPayment(amount, currency, customerInfo, paymentDetails);
        logTransaction(amount, currency, customerInfo, result);
        return result;
    }

    /**
     * Validates the payment input.
     *
     * @param amount       the payment amount
     * @param currency     the currency
     * @param customerInfo customer details
     * @return true if valid, false otherwise
     */
    private boolean validate(double amount, String currency, Map<String, String> customerInfo) {
        return amount > 0 && (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP"))
                && customerInfo.containsKey("email");
    }

    /**
     * Logs the transaction to the console.
     *
     * @param amount       the amount
     * @param currency     the currency
     * @param customerInfo the customer info
     * @param result       the result map
     */
    private void logTransaction(double amount, String currency, Map<String, String> customerInfo, Map<String, String> result) {
        System.out.println(String.format("Transaction Log: %.2f %s for %s - %s", amount, currency, customerInfo.get("name"), result));
    }
}