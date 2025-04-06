import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> customer = Map.of("name", "John Doe", "email", "john@example.com");
        Map<String, String> paymentDetails = Map.of("card_number", "123456789012", "expiry", "12/25", "cvv", "123");

        PaymentGateway stripe = new StripeGateway("https://api.stripe.com/process");
        PaymentGateway paypal = new PayPalGateway("https://api.paypal.com/process");

        PaymentProcessor processor = new PaymentProcessor(stripe);
        Map<String, String> result1 = processor.process(100, "USD", customer, paymentDetails);
        System.out.println("Stripe Result: " + result1);

        processor = new PaymentProcessor(paypal);
        Map<String, String> result2 = processor.process(150, "EUR", customer, paymentDetails);
        System.out.println("PayPal Result: " + result2);
    }
}