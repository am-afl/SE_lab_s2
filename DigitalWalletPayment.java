import java.util.*;

/**
 * Concrete class for digital wallet payments.
 */
class DigitalWalletPayment extends Payment {
    public DigitalWalletPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates the digital wallet payment details.
     */
    @Override
    public boolean validatePayment() {
        return amount > 0 &&
                (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP")) &&
                customerInfo.containsKey("email") &&
                paymentDetails.containsKey("wallet_id");
    }
}
