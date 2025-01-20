package hanghae.domain.domain;

import hanghae.domain.types.theater.ShowtimeCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Theater {

    private String theaterName;

    private ShowtimeCollection showtimeCollection;

    public static Theater of(String theaterName) {
        return new Theater(theaterName, new ShowtimeCollection());
    }
}
