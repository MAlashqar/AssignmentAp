package com.assignment.Assignment.beans;

import com.assignment.Assignment.enums.NewsStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Arabic title cannot be empty")
    private String titleArabic;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Arabic description cannot be empty")
    private String descriptionArabic;

    @NotNull(message = "Publish date cannot be null")
    private LocalDate publishDate;

    @NotEmpty(message = "Image URL cannot be empty")
    private String imageUrl;

    private NewsStatus status;

    private Boolean deleted;

    private Boolean deleteRequest;
}
