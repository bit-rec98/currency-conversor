import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        System.out.println("Bienvenido/a al conversor de monedas!");
        System.out.print("Ingresá el monto que querés convertir: ");
        double amount = scanner.nextDouble();

        System.out.print("Ingresá la divisa a convertir (USD, ARS, EUR, etc): ");
        String fromCurrency = scanner.next().toUpperCase();

        System.out.print("Ingresá la divisa objetivo (USD, ARS, EUR, etc): ");
        String toCurrency = scanner.next().toUpperCase();

        try {
            // Fetch exchange rate and convert the amount
            double rate = converter.getExchangeRate(fromCurrency, toCurrency);
            double convertedAmount = amount * rate;

            // Output the conversion result
            System.out.printf("Tasa de conversión: %.4f\n", rate);
            System.out.printf("%.2f %s = %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);

        } catch (Exception e) {
            // Handle any errors, like API failure or invalid input
            System.out.println("Error: " + e.getMessage());
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}
