
CREATE INDEX idx_movie_title_genre_release_date
    ON movie (genre, title, release_date DESC);
