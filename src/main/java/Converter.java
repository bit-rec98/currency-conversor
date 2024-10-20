import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Converter {

    // Base URL for ExchangeRate API (replace with your API key)
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/635a6d673be8f0aea80ebb3b/latest/";

    public double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String url = API_URL + fromCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener los datos de la API.");
        }

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        if (!jsonResponse.has("conversion_rates")) {
            throw new RuntimeException("Formato de respuesta inválido.");
        }

        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

        if (!conversionRates.has(toCurrency)) {
            throw new RuntimeException("Divisa no disponible: " + toCurrency);
        }

        return conversionRates.get(toCurrency).getAsDouble();
    }

}
