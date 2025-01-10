-- Movie 테이블 생성
CREATE TABLE movie (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       release_date DATE NOT NULL,
                       running_time INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       thumbnail_url VARCHAR(255),
                       title VARCHAR(255) NOT NULL,
                       genre ENUM('ACTION', 'HORROR', 'ROMANCE', 'SF') NOT NULL,
                       rating ENUM('ADULT', 'ALL', 'FIFTEEN', 'TWELVE') NOT NULL
);

-- Theater 테이블 생성
CREATE TABLE theater (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         name VARCHAR(255) NOT NULL
);

-- Movie_Theater 테이블 생성
CREATE TABLE movie_theater (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               movie_id BIGINT NOT NULL,
                               theater_id BIGINT NOT NULL,
                               FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
                               FOREIGN KEY (theater_id) REFERENCES theater(id) ON DELETE CASCADE
);

-- Screening 테이블 생성
CREATE TABLE screening (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           screening_at DATE NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           started_at TIMESTAMP NOT NULL,
                           ended_at TIMESTAMP NOT NULL,
                           movie_id BIGINT NOT NULL,
                           FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);

-- Seat 테이블 생성
CREATE TABLE seat (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      reserved BIT NOT NULL DEFAULT 0,
                      screening_id BIGINT NOT NULL,
                      seat_number VARCHAR(10) NOT NULL,
                      FOREIGN KEY (screening_id) REFERENCES screening(id) ON DELETE CASCADE
);
