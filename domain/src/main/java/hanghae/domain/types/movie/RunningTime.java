package hanghae.domain.types.movie;

import hanghae.domain.exception.movie.InvalidRunningTimeException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RunningTime {

    private final int minutes;

    public RunningTime(int runningTime) throws InvalidRunningTimeException {
        isValidRunningTime(runningTime);

        this.minutes = runningTime;
    }

    private void isValidRunningTime(int runningTime) throws InvalidRunningTimeException {
        if (runningTime <= 0) {
            throw new InvalidRunningTimeException("running time must be positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RunningTime that = (RunningTime) o;
        return minutes == that.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(minutes);
    }

    @Override
    public String toString() {
        return "RunningTime{" +
                "minutes=" + minutes +
                '}';
    }
}
