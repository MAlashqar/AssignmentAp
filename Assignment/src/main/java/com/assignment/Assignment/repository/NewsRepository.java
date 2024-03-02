package com.assignment.Assignment.repository;

import com.assignment.Assignment.entity.NewsEntity;
import com.assignment.Assignment.enums.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findAllByStatusAndDeletedFalse(NewsStatus status);

    Optional<NewsEntity> findByStatusAndDeletedFalse(NewsStatus status);

    Optional<NewsEntity> findByStatusAndIdAndDeletedFalse(NewsStatus status, Long id);

    List<NewsEntity> findByPublishDateBeforeAndDeletedFalse(LocalDate publishDate);
    // Method to find all news items where deleteRequestedByContentWriter is true
    List<NewsEntity> findByDeleteRequestTrue();

}
