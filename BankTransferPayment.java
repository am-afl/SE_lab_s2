import java.util.*;

/**
 * Concrete class for bank transfer payments.
 */
class BankTransferPayment extends Payment {
    public BankTransferPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates the bank transfer payment details.
     */
    @Override
    public boolean validatePayment() {
        return amount > 0 && (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP")) && customerInfo.containsKey("email") && paymentDetails.containsKey("account_number");
    }
}
