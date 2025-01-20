package hanghae.infrastructure.domain.converter;

import hanghae.domain.types.movie.ReleaseDate;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;

@Converter(autoApply = true)
public class ReleaseDateConverter implements AttributeConverter<ReleaseDate, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(ReleaseDate releaseDate) {
        return releaseDate == null ? null : releaseDate.getDate();
    }

    @Override
    public ReleaseDate convertToEntityAttribute(LocalDate localDate) {
        return localDate == null ? null : new ReleaseDate(localDate);
    }
}
