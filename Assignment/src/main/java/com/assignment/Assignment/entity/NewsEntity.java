package com.assignment.Assignment.entity;

import com.assignment.Assignment.enums.NewsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "title_arabic")
    private String titleArabic;

    @Column(name = "description")
    private String description;

    @Column(name = "description_arabic")
    private String descriptionArabic;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "image_url")
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NewsStatus status;

    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "deleteRequest")
    private Boolean deleteRequest;

}

