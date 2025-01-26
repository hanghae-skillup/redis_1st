SET NAMES utf8mb4;
SET CHARACTER_SET_SERVER = 'utf8mb4';

-- User 테이블 생성
CREATE TABLE user
(
    created_at datetime(6)  null,
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    updated_at datetime(6)  null,
    email      varchar(255) null,
    password   varchar(255) null,
    username   varchar(255) null
);

-- Movie 테이블 생성
CREATE TABLE movie (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
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
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    name VARCHAR(255) NOT NULL
);

-- Screening 테이블 생성
CREATE TABLE screening (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    screening_at DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP NOT NULL,
    movie_id INT UNSIGNED NOT NULL,
    theater_id INT UNSIGNED NOT NULL
);

-- Seat 테이블 생성
CREATE TABLE seat (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    theater_id INT UNSIGNED NOT NULL,
    seat_number VARCHAR(10) NOT NULL
);

-- Reservation 테이블 생성
CREATE TABLE reservation
(
    created_at   datetime(6)  null,
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    screening_id INT UNSIGNED NOT NULL,
    updated_at   datetime(6)  null,
    user_id      INT UNSIGNED NOT NULL
);
-- ReservedSeat 테이블 생성
CREATE TABLE reserved_seat
(
    created_at     datetime(6)  null,
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    reservation_id int unsigned null,
    seat_id        int unsigned null,
    updated_at     datetime(6)  null
);
