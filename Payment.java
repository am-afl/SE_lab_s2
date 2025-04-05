import java.util.*;

/**
 * Abstract class representing a general payment.
 * It holds common fields and requires subclasses to implement validation logic.
 */
public abstract class Payment {
    protected double amount;
    protected String currency;
    protected Date timestamp;
    protected Map<String, String> customerInfo;
    protected Map<String, String> paymentDetails;

    /**
     * Constructor for Payment.
     *
     * @param amount         Payment amount
     * @param currency       Payment currency (e.g., USD, EUR)
     * @param customerInfo   Customer information (name, email, etc.)
     * @param paymentDetails Specific details for payment method
     */
    public Payment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.timestamp = new Date();
        this.customerInfo = customerInfo;
        this.paymentDetails = paymentDetails;
    }

    /**
     * Abstract method for validating the payment.
     *
     * @return true if valid, false otherwise
     */
    public abstract boolean validatePayment();

}
