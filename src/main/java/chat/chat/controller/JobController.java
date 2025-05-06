package chat.chat.controller;

import chat.chat.model.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JobController {

    @GetMapping("/jobs")
    public String showJobs(Model model) {
        // Placeholder: Fetch all jobs
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Frontend Developer", "Modern web uygulamaları geliştirme", "https://linkedin.com/job1"));
        jobs.add(new Job("Backend Java Developer", "Java ile backend sistemler geliştirme", "https://linkedin.com/job2"));
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/jobs/search")
    public String searchJobs(@RequestParam("query") String query, Model model) {
        // Placeholder: Filter jobs by query
        List<Job> allJobs = new ArrayList<>();
        allJobs.add(new Job("Frontend Developer", "Modern web uygulamaları geliştirme", "https://linkedin.com/job1"));
        allJobs.add(new Job("Backend Java Developer", "Java ile backend sistemler geliştirme", "https://linkedin.com/job2"));

        List<Job> filteredJobs = allJobs.stream()
            .filter(job -> job.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                          job.getDescription().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());

        model.addAttribute("jobs", filteredJobs);
        model.addAttribute("query", query);
        return "jobs";
    }
}