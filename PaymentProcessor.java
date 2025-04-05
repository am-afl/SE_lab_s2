import java.util.*;

/**
 * Processor class responsible for coordinating payment validation and gateway execution.
 */
class PaymentProcessor {
    private Map<String, PaymentGateway> gateways;

    /**
     * Constructor to inject payment gateways.
     *
     * @param gateways map of payment type to gateway
     */
    public PaymentProcessor(Map<String, PaymentGateway> gateways) {
        this.gateways = gateways;
    }

    /**
     * Processes a payment by type and object.
     *
     * @param type    the payment type key (e.g., "credit_card")
     * @param payment the payment object
     * @return result map containing status and transaction ID or error
     */
    public Map<String, String> processPayment(String type, Payment payment) {
        if (!payment.validatePayment()) {
            return Map.of("status", "failed", "message", "Validation error");
        }

        PaymentGateway gateway = gateways.get(type);
        if (gateway == null) {
            return Map.of("status", "failed", "message", "Unsupported payment type");
        }

        Map<String, String> result = gateway.processPayment(payment);
        logTransaction(payment, result);
        return result;
    }

    /**
     * Logs the transaction for record keeping.
     */
    private void logTransaction(Payment payment, Map<String, String> result) {
        String log = String.format("%s - Payment of %.2f %s for %s: %s",
                new Date(), payment.amount, payment.currency,
                payment.customerInfo.get("name"), result);
        System.out.println("LOG: " + log);
    }
}
