package chat.chat.controller;
import chat.chat.model.Preference;
import chat.chat.service.PreferenceService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private final PreferenceService preferenceService;

    public DashboardController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String userId = userDetails.getUsername();
            List<Preference> preferences = preferenceService.getPreferencesByUserId(userId);
            model.addAttribute("preferences", preferences);
        } else {
            model.addAttribute("preferences", new ArrayList<>());
        }
    
        // Yeni bir Preference nesnesi ekleyin
        model.addAttribute("preference", new Preference());
    
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String savePreferences(@ModelAttribute Preference preference, 
                                 @AuthenticationPrincipal UserDetails userDetails, 
                                 RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            redirectAttributes.addFlashAttribute("message", "Tercihleri kaydetmek için lütfen giriş yapın!");
            return "redirect:/login";
        }

        try {
            String userId = userDetails.getUsername();
            preferenceService.savePreference(userId, preference);
            redirectAttributes.addFlashAttribute("message", "Tercihler başarıyla kaydedildi!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Tercihler kaydedilirken bir hata oluştu: " + e.getMessage());
        }

        return "redirect:/dashboard";
    }
}