package chat.chat.controller;

import chat.chat.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/chat")
    public String chatWithBot(@RequestParam String message, Model model) {
        String response = chatService.askDeepSeek(message).block();
        model.addAttribute("response", response);
        return "index";
    }
}
