package chat.chat.controller;

import chat.chat.model.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        System.out.println("Method Running: showDashboard");
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Frontend Developer", "React deneyimi olan frontend geliştirici", "https://linkedin.com/job1"));
        jobs.add(new Job("Backend Java Developer", "Spring Boot tecrübeli backend geliştirici", "https://linkedin.com/job2"));

        model.addAttribute("jobs", jobs);
        return "dashboard";
    }
}
