package com.example.application.dto.request;

import com.example.domain.model.valueObject.ContentRating;
import com.example.domain.model.valueObject.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MovieRequestDto(

        @NotNull(message = "Title must not be null")
        @Size(min = 1, message = "Title must not be empty")
        String title,

        @NotNull(message = "Content rating must not be null")
        ContentRating contentRating,

        @NotNull(message = "Release date must not be null")
        LocalDate releaseDate,

        @NotNull(message = "Thumbnail URL must not be null")
        @Size(min = 1, message = "Thumbnail URL must not be empty")
        @Pattern(
                regexp = "^(https?|ftp)://.*$",
                message = "Thumbnail URL must be a valid URL format (http or https)"
        )
        String thumbnailUrl,

        @Min(value = 1, message = "Runtime must be at least 1 minute")
        int runtimeMinutes,

        @NotNull(message = "Genre must not be null")
        Genre genre

) {}
