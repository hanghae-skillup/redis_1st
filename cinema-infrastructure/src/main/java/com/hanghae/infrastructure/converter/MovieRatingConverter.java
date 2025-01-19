package com.hanghae.infrastructure.converter;

import com.hanghae.domain.model.enums.MovieRating;
import jakarta.persistence.AttributeConverter;

public class MovieRatingConverter implements AttributeConverter<MovieRating, String> {
    /**
     *  MovieRating enum 코드변환 메서드
     *  DB저장시 코드 변환해 저장
     *  DB조회시 다시 enum코드로 변환해서 반환
     */

    @Override
    public String convertToDatabaseColumn(MovieRating attribute) {
        //enum코드 db코드로 변환
        return attribute != null ? attribute.getDbCode() : null;
    }

    @Override
    public MovieRating convertToEntityAttribute(String dbData) {
        //db코드 enum코드로 변환
        return dbData != null ? MovieRating.fromDbCode(dbData) : null;
    }
}
