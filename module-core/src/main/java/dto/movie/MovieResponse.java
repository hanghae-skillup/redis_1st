package dto.movie;

import java.time.LocalDate;
import java.util.Objects;

import dto.genre.GenreResponse;
import dto.rating.RatingResponse;
import lombok.Getter;

@Getter
public class MovieResponse {
	private Long movieId;
	private String title;
	private LocalDate openDay;
	private Integer runningTimeAsMinute;
	private String thumbnail;
	private GenreResponse genre;
	private RatingResponse rating;
	private String plot;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true; // 같은 객체라면 true
		if (obj == null || getClass() != obj.getClass()) return false; // 클래스 타입 확인
		MovieResponse mObj = (MovieResponse) obj; // 안전한 캐스팅
		return movieId == mObj.movieId; // id 비교
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieId); // id를 기준으로 hashCode 생성
	}
}
