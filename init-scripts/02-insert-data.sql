-- Movie 테이블 데이터 삽입
DELIMITER $$

CREATE PROCEDURE insert_movie()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE rating ENUM('ALL', 'AGE_12', 'AGE_15', 'AGE_19', 'RESTRICTED');
    DECLARE genre ENUM('ACTION', 'COMEDY', 'DRAMA', 'HORROR', 'ROMANCE', 'THRILLER');
    DECLARE release_date DATE;
    DECLARE running_time INT;

    WHILE i <= 500 DO
        SET rating = ELT(FLOOR(1 + (RAND() * 5)), 'ALL', 'AGE_12', 'AGE_15', 'AGE_19', 'RESTRICTED');
        SET genre = ELT(FLOOR(1 + (RAND() * 6)), 'ACTION', 'COMEDY', 'DRAMA', 'HORROR', 'ROMANCE', 'THRILLER');
        SET release_date = DATE_ADD('2024-01-01', INTERVAL FLOOR(RAND() * 365) DAY);
        SET running_time = FLOOR(90 + (RAND() * 30)); -- 90 to 120 minutes

        INSERT INTO movie (title, rating, release_date, thumbnail, running_time, genre)
        VALUES (CONCAT('Movie', i), rating, release_date, 'https://xxx', running_time, genre);

        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;


-- Theater 테이블 데이터 삽입
DELIMITER $$

CREATE PROCEDURE insert_theater()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 1000 DO
        INSERT INTO theater (name)
        VALUES (CONCAT('Theater', i));
        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;


-- Seat 테이블 데이터 삽입
DELIMITER $$

CREATE PROCEDURE insert_seats()
BEGIN
    DECLARE theater INT DEFAULT 1;
    DECLARE seat_row CHAR(1);
    DECLARE seat_column INT;

    -- Theater1부터 Theater1000까지 반복
    WHILE theater <= 1000 DO
        -- A부터 E까지 row를 반복
        SET seat_row = 'A';
        WHILE seat_row <= 'E' DO
            -- 1부터 5까지 column을 반복
            SET seat_column = 1;
            WHILE seat_column <= 5 DO
                INSERT INTO seat (theater_id, seat_row, seat_column)
                VALUES (theater, seat_row, seat_column);
                SET seat_column = seat_column + 1;
            END WHILE;
            SET seat_row = CHAR(ASCII(seat_row) + 1); -- 다음 row로 변경 (A -> B -> C -> D -> E)
        END WHILE;
        SET theater = theater + 1; -- 다음 theater로 변경
    END WHILE;
END $$

DELIMITER ;


-- Screening 테이블 데이터 삽입
DELIMITER $$

CREATE PROCEDURE insert_screening()
BEGIN
    DECLARE movie_id INT;
    DECLARE theater_id INT;
    DECLARE start_time TIME;
    DECLARE i INT DEFAULT 1;

    -- 영화에 대해 반복 (Movie1부터 Movie500까지)
    WHILE i <= 500 DO
        SET movie_id = i;

        -- 각 영화에 대해 2개의 상영관 (Theater1, Theater2, ...)에서 상영
        SET theater_id = (i * 2) - 1; -- Movie1은 Theater1, Theater2에서 상영
        SET start_time = '09:00';
        INSERT INTO screening (movie_id, theater_id, start_time) VALUES (movie_id, theater_id, start_time);

        SET start_time = '11:00';
        INSERT INTO screening (movie_id, theater_id, start_time) VALUES (movie_id, theater_id, start_time);

        SET theater_id = i * 2; -- Movie1은 Theater2에서 상영, Movie2는 Theater4에서 상영

        SET start_time = '09:00';
        INSERT INTO screening (movie_id, theater_id, start_time) VALUES (movie_id, theater_id, start_time);

        SET start_time = '11:00';
        INSERT INTO screening (movie_id, theater_id, start_time) VALUES (movie_id, theater_id, start_time);

        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;


CALL insert_movie();
CALL insert_theater();
CALL insert_seats();
CALL insert_screening();

COMMIT