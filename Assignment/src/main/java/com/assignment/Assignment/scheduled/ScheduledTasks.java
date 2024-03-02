package com.assignment.Assignment.scheduled;
import com.assignment.Assignment.security.JwtAuthenticationFilter;
import com.assignment.Assignment.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

@RequiredArgsConstructor

public class ScheduledTasks {

    private final NewsService newsService;

    // Schedule the soft deletion task to run every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void softDeleteExpiredNews() {
        newsService.softDeleteExpiredNews();
    }

    //Clear The set token every 1 min
    @Scheduled(cron = "0 * * * * *")
    public void clearingTheSet() {
        System.out.println("Start clearing");
        JwtAuthenticationFilter.blacklist.clear();
    }
}