package dto.internal;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Getter;

@Getter
public class MovieDTO {
	private Long id;
	private String title;
	private LocalDate openDay;
	private Integer runningTime;
	private String thumbnail;
	private GenreDTO genre;
	private RatingDTO rating;
	private String plot;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true; // 같은 객체라면 true
		if (obj == null || getClass() != obj.getClass()) return false; // 클래스 타입 확인
		MovieDTO mObj = (MovieDTO) obj; // 안전한 캐스팅
		return id == mObj.id; // id 비교
	}

	@Override
	public int hashCode() {
		return Objects.hash(id); // id를 기준으로 hashCode 생성
	}
}
