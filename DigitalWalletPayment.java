import java.util.*;

class DigitalWalletPayment extends Payment {
    public DigitalWalletPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    @Override
    public boolean validatePayment() {
        return amount > 0 &&
                (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP")) &&
                customerInfo.containsKey("email") &&
                paymentDetails.containsKey("wallet_id");
    }
}
