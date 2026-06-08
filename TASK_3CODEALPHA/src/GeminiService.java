import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeminiService {

    // ✅ PUT YOUR REAL API KEY HERE
    private static final String API_KEY = "gemini-apikey";

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String askGemini(String prompt) {

        try {

            String json = """
            {
              "contents": [{
                "parts": [{
                  "text": "Answer professionally in 5 to 8 lines only: %s"
                }]
              }]
            }
            """.formatted(prompt.replace("\"", "\\\""));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                                    + API_KEY))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(15))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // ❗ HANDLE HTTP ERRORS FIRST
            if (response.statusCode() != 200) {
                return "⚠ API Error (HTTP " + response.statusCode() + "): Try again later.";
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            // ❗ HANDLE GEMINI ERROR RESPONSE
            if (root.has("error")) {
                return "⚠ Gemini API busy. Please try again in a few seconds.";
            }

            JsonNode candidates = root.path("candidates");

            if (!candidates.isArray() || candidates.size() == 0) {
                return "⚠ No response received from Gemini.";
            }

            JsonNode textNode = candidates
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            if (textNode.isMissingNode()) {
                return "⚠ Invalid response format from API.";
            }

            return textNode.asText();

        } catch (Exception e) {
            return "⚠ Error: " + e.getMessage();
        }
    }
}