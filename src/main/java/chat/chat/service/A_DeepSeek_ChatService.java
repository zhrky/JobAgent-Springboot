package chat.chat.service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class A_DeepSeek_ChatService {

    private final ChatCompletionsClient client;

    public A_DeepSeek_ChatService(
            @Value("${azure.deepseek.api.key}") String apiKey,
            @Value("${azure.deepseek.endpoint}") String endpoint,
            @Value("${azure.deepseek.deployment.name}") String deploymentName) {

        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();
    }

    public String askAzureOpenAI(String userMessage) {
        ChatRequestMessage message = new ChatRequestUserMessage(userMessage);
        ChatCompletionsOptions options = new ChatCompletionsOptions(Collections.singletonList(message));
        options.setMaxTokens(1000);
        options.setTemperature(0.7);

        ChatCompletions completions = client.complete(options);
        return completions.getChoices().get(0).getMessage().getContent();
    }
}

