CREATE INDEX idx_movie_search ON movie (release_date DESC);
CREATE INDEX idx_genre_movie ON movie (genre_id);
CREATE INDEX idx_screening_lookup ON screening (movie_id, screening_time, status);