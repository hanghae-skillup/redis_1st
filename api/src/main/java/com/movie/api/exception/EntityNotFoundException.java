package com.movie.api.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {
    private static final String CODE = "ENTITY_NOT_FOUND";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String entityName) {
        super(entityName + " not found", STATUS, CODE);
    }
} 