package com.example.domain.model.valueObject;

import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Genre {

    THRILLER(1),
    ACTION(2),
    DRAMA(3),
    COMEDY(4),
    SCI_FI(5),
    ;

    private final int dbValue;

    @JsonCreator
    public static Genre fromJson(String value) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new CustomException(ErrorCode.INVALID_GENRE);
    }

    private static final Map<Integer, Genre> MAP =
            Stream.of(values()).collect(Collectors.toMap(Genre::getDbValue, genre -> genre));

    public static Genre fromDbValue(int dbValue) {
        Genre genre = MAP.get(dbValue);
        if (genre == null) {
            throw new CustomException(ErrorCode.INVALID_GENRE);
        }
        return genre;
    }

}
