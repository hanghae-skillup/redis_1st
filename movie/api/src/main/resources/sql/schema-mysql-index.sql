CREATE INDEX idx_movie_movie_id ON movie (movie_id);
CREATE INDEX idx_movie_release_date ON movie (release_date DESC);
CREATE INDEX idx_movie_genre_id ON movie (genre_id);
CREATE INDEX idx_movie_genre_id_title_release_date ON movie (genre_id, title, release_date DESC);
CREATE INDEX idx_screening_movie_id_screening_time_status ON screening (movie_id, screening_time, status);