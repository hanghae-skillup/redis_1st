package hanghae.domain.types.movie;


import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class ReleaseDate {

    private final LocalDate date;

    public ReleaseDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public ReleaseDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleaseDate that = (ReleaseDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }

    @Override
    public String toString() {
        return "ReleaseDate{" +
                "date=" + date +
                '}';
    }
}
