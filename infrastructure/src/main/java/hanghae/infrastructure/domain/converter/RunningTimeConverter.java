package hanghae.infrastructure.domain.converter;

import hanghae.domain.types.movie.RunningTime;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RunningTimeConverter implements AttributeConverter<RunningTime, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RunningTime runningTime) {
        return runningTime == null ? null : runningTime.getMinutes();
    }

    @Override
    public RunningTime convertToEntityAttribute(Integer integer) {
        return integer == null ? null : new RunningTime(integer);
    }
}
