package project.redis.application.screening.port.outbound;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningQueryFilter {
    private int maxScreeningDay;
    private String movieName;
    private String genreName;

    @Override
    public int hashCode() {
        return Objects.hash(maxScreeningDay, genreName, movieName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningQueryFilter that = (ScreeningQueryFilter) o;

        return maxScreeningDay == that.maxScreeningDay &&
                Objects.equals(genreName, that.genreName) &&
                Objects.equals(movieName, that.movieName);
    }
}
