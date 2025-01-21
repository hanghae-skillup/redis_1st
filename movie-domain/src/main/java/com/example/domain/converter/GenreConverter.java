package com.example.domain.converter;

import com.example.domain.model.valueObject.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Genre genre) {
        if (genre == null) { // 조건 검색 시 null 값 허용으로 장르 필터가 적용되지 않게 처리
            return null;
        }
        return genre.getDbValue();
    }

    @Override
    public Genre convertToEntityAttribute(Integer dbData) {
        assert dbData != null : "[ERROR] Genre can not be null";
        return Genre.fromDbValue(dbData);
    }

}
