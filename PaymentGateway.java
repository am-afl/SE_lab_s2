import java.util.Map;

/**
 * Interface for payment gateway integrations.
 */
public interface PaymentGateway {
    /**
     * Processes a payment.
     *
     * @param amount         the amount to be paid
     * @param currency       the currency of the payment
     * @param customerInfo   information about the customer
     * @param paymentDetails details specific to the payment method
     * @return a map containing the result of the payment
     */
    Map<String, String> processPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails);

    /**
     * Refunds a previously made payment.
     *
     * @param transactionId the ID of the transaction to refund
     * @return a map containing the result of the refund
     */
    Map<String, String> refundPayment(String transactionId);

    /**
     * Retrieves the status of a transaction.
     *
     * @param transactionId the ID of the transaction
     * @return the status of the transaction
     */
    String getTransactionStatus(String transactionId);
}