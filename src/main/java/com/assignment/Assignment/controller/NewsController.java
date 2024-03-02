package com.assignment.Assignment.controller;

import com.assignment.Assignment.beans.NewsDTO;
import com.assignment.Assignment.entity.NewsEntity;
import com.assignment.Assignment.enums.NewsStatus;
import com.assignment.Assignment.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PreAuthorize("hasAnyRole('CONTENT_WRITER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@Valid @RequestBody NewsDTO news) {
        NewsDTO createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        NewsDTO news = newsService.getNewsById(id);
        if (news != null) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> newsList = newsService.getAllNews();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CONTENT_WRITER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @Valid @RequestBody NewsDTO news) {
        NewsDTO updatedNews = newsService.updateNews(id, news);
        if (updatedNews != null) {
            return new ResponseEntity<>(updatedNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateNewsStatus(@PathVariable Long id, @RequestParam NewsStatus status) {
        boolean updated = newsService.updateNewsStatus(id, status);
        if (updated) {
            return ResponseEntity.ok("News status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("News not found with id: " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending")
    public List<NewsEntity> getAllPendingNews() {
        return newsService.getAllNewsByStatus(NewsStatus.PENDING);
    }

    @PreAuthorize("hasAnyRole('CONTENT_WRITER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNewsByContentWriter(@PathVariable Long id) {
        boolean deleted = newsService.deleteNewsByContentWriter(id);
        if (deleted) {
            return ResponseEntity.ok("News item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this news item,flag it for admin approval");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/admin")
    public ResponseEntity<String> deleteNewsByAdmin(@PathVariable Long id) {
        boolean deleted = newsService.deleteNewsByAdmin(id);
        if (deleted) {
            return ResponseEntity.ok("News item deleted successfully by admin");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("News item not found with id: " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approveDeleteRequest")
    public List<NewsEntity> getAllNewsWithDeleteRequests() {
        return newsService.getAllNewsWithDeleteRequests();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approveDeleteRequest/{id}")
    public ResponseEntity<String> approveDeleteRequestByAdmin(@PathVariable Long id) {
        boolean approved = newsService.approveDeleteRequestByAdmin(id);
        if (approved) {
            return ResponseEntity.ok("Deletion request approved by Admin");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deletion request not found or already approved");
        }
    }

}

