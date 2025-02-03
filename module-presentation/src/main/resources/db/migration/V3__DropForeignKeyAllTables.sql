ALTER TABLE movie DROP FOREIGN KEY fk_movie_genre;
ALTER TABLE screening DROP FOREIGN KEY fk_screening_movie;
ALTER TABLE screening DROP FOREIGN KEY fk_screening_cinema;
ALTER TABLE seat DROP FOREIGN KEY fk_seat_cinema;