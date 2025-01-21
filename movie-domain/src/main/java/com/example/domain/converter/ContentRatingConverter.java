package com.example.domain.converter;

import com.example.domain.model.valueObject.ContentRating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // 모든 ContentRating 필드에 자동으로 적용
public class ContentRatingConverter implements AttributeConverter<ContentRating, String> {

    @Override
    public String convertToDatabaseColumn(ContentRating contentRating) {
        assert contentRating != null : "[ERROR] ContentRating is null.";
        return contentRating.getDbValue();
    }

    @Override
    public ContentRating convertToEntityAttribute(String dbData) {
        assert dbData != null : "[ERROR] ContentRating from DB value is null.";
        return ContentRating.fromDbValue(dbData);
    }

}
