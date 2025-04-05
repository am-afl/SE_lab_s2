import java.util.*;

/**
 * Interface defining the behavior of a payment gateway.
 */
interface PaymentGateway {
    /**
     * Processes the given payment.
     *
     * @param payment the payment to process
     * @return result map containing status and transaction ID
     */
    Map<String, String> processPayment(Payment payment);
}