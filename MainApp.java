import java.util.*;

class MainApp {
    public static void main(String[] args) {
        Map<String, String> customer = Map.of("name", "John Doe", "email", "john@example.com");
        Map<String, String> paymentDetails = Map.of("card_number", "123456789012", "expiry", "12/25", "cvv", "123");

        Payment payment = new CreditCardPayment(100, "USD", customer, paymentDetails);

        Map<String, PaymentGateway> gateways = Map.of(
                "credit_card", new CreditCardGateway("https://api.creditcard.com/process"),
                "digital_wallet", new DigitalWalletGateway("https://api.digitalwallet.com/process"),
                "bank_transfer", new BankTransferGateway("https://api.banktransfer.com/process")
        );

        PaymentProcessor processor = new PaymentProcessor(gateways);
        Map<String, String> result = processor.processPayment("credit_card", payment);

        System.out.println("Final Result: " + result);
    }
}