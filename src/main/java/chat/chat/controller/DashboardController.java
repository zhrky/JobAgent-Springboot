package chat.chat.controller;

import chat.chat.model.Job;
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
import java.util.Optional;

@Controller
public class DashboardController {

    private final PreferenceService preferenceService;

    public DashboardController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Load recent jobs
        List<Job> recentJobs = new ArrayList<>();
        recentJobs.add(new Job("Frontend Developer", "Modern web uygulamaları geliştirme", "https://linkedin.com/job1"));
        recentJobs.add(new Job("Backend Java Developer", "Java ile backend sistemler geliştirme", "https://linkedin.com/job2"));
        model.addAttribute("recentJobs", recentJobs);

        // Load user preferences if authenticated
        Preference preference = new Preference();
        if (userDetails != null) {
            try {
                String userId = userDetails.getUsername();
                Optional<Preference> savedPreference = preferenceService.getPreferenceByUserId(userId);
                preference = savedPreference.orElse(new Preference());
            } catch (Exception e) {
                model.addAttribute("error", "Tercihler yüklenirken bir hata oluştu: " + e.getMessage());
            }
        }

        // Always add preference to model
        model.addAttribute("preference", preference);

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