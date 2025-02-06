package com.example.application.dto.request;

import com.example.domain.model.valueObject.Genre;

public record MovieSearchCriteria(
        String title,
        Genre genre
) {
}
