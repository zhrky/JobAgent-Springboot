package chat.chat.controller;

import chat.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

// This is a Spring Boot REST controller that handles chat requests.
// It uses the ChatService to interact with the DeepSeek API.

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    private final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Mono<String> chatWithBot(@RequestBody String message) {
        return chatService.askDeepSeek(message);
    }
}

