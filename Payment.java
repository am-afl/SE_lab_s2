import java.util.*;

public abstract class Payment {
    protected double amount;
    protected String currency;
    protected Date timestamp;
    protected Map<String, String> customerInfo;
    protected Map<String, String> paymentDetails;

    public Payment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.timestamp = new Date();
        this.customerInfo = customerInfo;
        this.paymentDetails = paymentDetails;
    }

    public abstract boolean validatePayment();

}
