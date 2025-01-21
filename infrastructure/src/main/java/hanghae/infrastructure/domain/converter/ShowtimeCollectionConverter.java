package hanghae.infrastructure.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanghae.domain.domain.Showtime;
import hanghae.domain.types.theater.ShowtimeCollection;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = true)
public class ShowtimeCollectionConverter implements AttributeConverter<ShowtimeCollection, String> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ShowtimeCollection showtimeCollection) {

        if (showtimeCollection == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(showtimeCollection);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("failed to convert ShowtimeCollection to JSON", e);
        }
    }

    @Override
    public ShowtimeCollection convertToEntityAttribute(String dbData) {

        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        try {
            return new ShowtimeCollection(objectMapper.readValue(dbData, new TypeReference<List<Showtime>>() {}));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("failed to convert JSON to ShowtimeCollection", e);
        }
    }
}
