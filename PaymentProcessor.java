import java.util.*;

class PaymentProcessor {
    private Map<String, PaymentGateway> gateways;

    public PaymentProcessor(Map<String, PaymentGateway> gateways) {
        this.gateways = gateways;
    }

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

    private void logTransaction(Payment payment, Map<String, String> result) {
        String log = String.format("%s - Payment of %.2f %s for %s: %s",
                new Date(), payment.amount, payment.currency,
                payment.customerInfo.get("name"), result);
        System.out.println("LOG: " + log);
    }
}
