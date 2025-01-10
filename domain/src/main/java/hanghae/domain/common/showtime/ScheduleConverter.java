package hanghae.domain.common.showtime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ScheduleConverter implements AttributeConverter<Schedule, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Schedule schedule) {

        if (schedule == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(schedule);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("failed to convert Schedule to JSON", e);
        }
    }

    @Override
    public Schedule convertToEntityAttribute(String dbData) {

        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(dbData, Schedule.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("failed to convert JSON to Schedule", e);
        }
    }
}
