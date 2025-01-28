package org.haas.core.domain.exception;

import org.haas.core.domain.movie.Movie;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }
}
