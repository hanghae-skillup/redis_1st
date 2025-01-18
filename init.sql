-- Table Definitions
CREATE TABLE IF NOT EXISTS movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    rating ENUM('GENERAL', 'PG13', 'R', 'NC17') NOT NULL,
    release_date DATETIME NOT NULL,
    thumbnail_url VARCHAR(255) NOT NULL,
    running_time INT NOT NULL,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS screenings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    cinema_name VARCHAR(255) NOT NULL,
    start_time DATETIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

-- Data Insertion
INSERT INTO movies (title, rating, release_date, thumbnail_url, running_time, genre)
VALUES ('Movie 1', 'GENERAL', NOW(), 'http://example.com/thumbnail1.jpg', 120, 'ACTION');

INSERT INTO screenings (movie_id, cinema_name, start_time)
VALUES 
    (1, 'Cinema A', NOW()),
    (1, 'Cinema B', NOW() + INTERVAL '2 HOUR');