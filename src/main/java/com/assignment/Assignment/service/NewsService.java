package com.assignment.Assignment.service;

import com.assignment.Assignment.beans.NewsDTO;
import com.assignment.Assignment.dto.NewsMapper;
import com.assignment.Assignment.dto.UserMapper;
import com.assignment.Assignment.entity.NewsEntity;
import com.assignment.Assignment.enums.NewsStatus;
import com.assignment.Assignment.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {


    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    public NewsDTO createNews(NewsDTO news) {
        news.setStatus(NewsStatus.PENDING);
        news.setDeleted(false);
        news.setDeleteRequest(false);
        return newsMapper.newsEntityToDTO(newsRepository.save(newsMapper.newsDTOToEntity(news)));
    }


    public NewsDTO getNewsById(Long id) {
        return newsMapper.newsEntityToDTO(newsRepository.findByStatusAndIdAndDeletedFalse(NewsStatus.ACCEPTED, id).orElse(null));
    }


    public List<NewsDTO> getAllNews() {
        return newsRepository.findAllByStatusAndDeletedFalse(NewsStatus.ACCEPTED).stream().map(x -> newsMapper.newsEntityToDTO(x)).collect(Collectors.toList());
    }


    public NewsDTO updateNews(Long id, NewsDTO news) {
        NewsEntity entity=newsRepository.findById(id).orElse(null);

        if (null!=entity) {
            news.setId(id);
            news.setStatus(entity.getStatus());
            news.setDeleted(entity.getDeleted());
            news.setDeleteRequest(entity.getDeleteRequest());
            return newsMapper.newsEntityToDTO(newsRepository.save(newsMapper.newsDTOToEntity(news)));
        }
        return null;
    }


    public boolean updateNewsStatus(Long id, NewsStatus status) {
        NewsEntity newsEntity = newsRepository.findById(id).orElse(null);
        if (newsEntity != null) {
            // Update the status
            newsEntity.setStatus(status);
            newsRepository.save(newsEntity);
            return true;
        }
        return false;
    }

    public List<NewsEntity> getAllNewsByStatus(NewsStatus status) {
        return newsRepository.findAllByStatusAndDeletedFalse(status);
    }

    public boolean deleteNewsByContentWriter(Long id) {
        Optional<NewsEntity> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            NewsEntity news = optionalNews.get();
            if (news.getStatus() == NewsStatus.PENDING) {
                newsRepository.delete(news);
                return true;
            }

            // If news is not pending, flag it for admin approval
            news.setDeleteRequest(true);
            newsRepository.save(news);

        }
        return false;
    }

    // Method for Admins to delete news
    public boolean deleteNewsByAdmin(Long id) {
        Optional<NewsEntity> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            newsRepository.delete(optionalNews.get());
            return true;
        }
        return false;

    }

    public void softDeleteExpiredNews() {
        LocalDate currentDate = LocalDate.now();
        List<NewsEntity> expiredNews = newsRepository.findByPublishDateBeforeAndDeletedFalse(currentDate);
        for (NewsEntity news : expiredNews) {
            news.setDeleted(true);
            newsRepository.save(news);
        }
    }
    public List<NewsEntity> getAllNewsWithDeleteRequests() {
        return newsRepository.findByDeleteRequestTrue();
    }

    public boolean approveDeleteRequestByAdmin(Long id) {
        Optional<NewsEntity> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            NewsEntity news = optionalNews.get();
            if (news.getDeleteRequest()) {
                // If deletion was requested by Content Writer, delete the news
                newsRepository.delete(news);
                return true; // Deletion request approved and news deleted
            }
        }
        return false; // No deletion request found or already approved
    }
}