import java.util.*;

class CreditCardPayment extends Payment {
    public CreditCardPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    @Override
    public boolean validatePayment() {
        return amount > 0 &&
                (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP")) &&
                customerInfo.containsKey("email") &&
                paymentDetails.getOrDefault("card_number", "").length() >= 12;
    }
}