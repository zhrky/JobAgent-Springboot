package chat.chat.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final WebClient webClient;

    // Constructor injection for WebClient
    // and API key
    public ChatService(@Value("${deepseek.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.deepseek.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Sends a message to the DeepSeek API and returns the response.
     *
     * @param userMessage The message to send to the API.
     * @return A Mono containing the response from the API.
     */
    // This method sends a message to the DeepSeek API and returns the response.
    public Mono<String> askDeepSeek(String userMessage) {
        Map<String, Object> requestBody = Map.of(
            "model", "deepseek-chat",
            "messages", List.of(
                Map.of("role", "user", "content", userMessage)
            )
        );

        // Send a POST request to the DeepSeek API with the request body
        // and retrieve the response as a Mono<JsonNode>
        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("choices").get(0).get("message").get("content").asText());
    }
}
