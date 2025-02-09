package module.repository.showing;

import static com.querydsl.core.group.GroupBy.*;
import static module.entity.QGenre.*;
import static module.entity.QMovie.*;
import static module.entity.QRating.*;
import static module.entity.QScreen.*;
import static module.entity.QShowing.*;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dto.genre.GenreResponse;
import dto.movie.MovieResponse;
import dto.movie.MovieShowingResponse;
import dto.rating.RatingResponse;
import dto.screen.ScreenResponse;
import dto.showing.ShowingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ShowingCustomRepositoryImpl implements ShowingCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<MovieShowingResponse> getShowingByMovieTitleAndGenre(String title, Long genreId) {
		BooleanBuilder conditionBuilder = new BooleanBuilder();

		if(title != null){
			conditionBuilder.and(showing.movie.title.contains(title));
		}

		if(genreId != null){
			conditionBuilder.and(showing.movie.genre.genreId.eq(genreId));
		}

		return jpaQueryFactory
			// 1) “from” 절
			.from(showing)
			.join(showing.movie, movie)
			.join(movie.genre, genre)
			.join(movie.rating, rating)
			.join(showing.screen, screen)

			// 2) 조건
			.where(
				showing.showStTime.after(LocalDateTime.now())
					.and(conditionBuilder)
			)

			// 3) transform(...) + groupBy
			.transform(
				groupBy(movie.movieId).list( // movieId를 기준으로 그룹핑
					Projections.fields(
						MovieShowingResponse.class,
						// ┌ movie 필드 매핑
						Projections.fields(
							MovieResponse.class,
							movie.movieId.as("movieId"),
							movie.title.as("title"),
							movie.openDay.as("openDay"),
							movie.runningTimeAsMinute.as("runningTimeAsMinute"),
							movie.thumbnail.as("thumbnail"),
							movie.plot.as("plot"),
							Projections.fields(
								GenreResponse.class,
								genre.genreId.as("genreId"),
								genre.genreName.as("genreName")
							).as("genre"),
							Projections.fields(
								RatingResponse.class,
								rating.ratingName.as("ratingName"),
								rating.img.as("img")
							).as("rating")
						).as("movie"),

						// ┌ 여러 개가 될 showing 필드는 list(...)
						list(
							Projections.fields(
								ShowingResponse.class,
								showing.showingId.as("showingId"),
								showing.showStTime.as("showStTime"),
								showing.showEdTime.as("showEdTime"),
								Projections.fields(
									ScreenResponse.class,
									screen.screenId.as("screenId"),
									screen.screenName.as("screenName")
								).as("screen")
							)
						).as("showings") // ShowingResponse 목록을 MovieShowingResponse 내 "showings" 필드로 매핑
					)
				)
			);
	}
}
