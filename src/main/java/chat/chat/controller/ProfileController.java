package chat.chat.controller;

import java.util.ArrayList;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import chat.chat.model.Preference;
import chat.chat.service.PreferenceService;
import java.util.List;

@Controller
public class ProfileController {

    private final PreferenceService preferenceService;

    public ProfileController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping("/profile")
public String showProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    if (userDetails != null) {
        String username = userDetails.getUsername();
        System.out.println("Username: " + username);
        model.addAttribute("username", username);
        

        List<Preference> preferences = preferenceService.getPreferencesByUserId(username);
        model.addAttribute("preferences", preferences);

        model.addAttribute("email", username);
    } else {
        model.addAttribute("username", "Anonim Kullanıcı");
        model.addAttribute("preferences", new ArrayList<>());
        model.addAttribute("email", "Bilinmiyor");
    }

    return "profile";
}
}