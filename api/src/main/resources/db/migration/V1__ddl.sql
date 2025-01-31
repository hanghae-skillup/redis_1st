CREATE TABLE Movie (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       rating ENUM('ALL', 'AGE_12', 'AGE_15', 'AGE_18') NOT NULL,
                       release_date TIMESTAMP NOT NULL,
                       thumbnail VARCHAR(255) NOT NULL,
                       genre ENUM('ACTION', 'ROMANCE', 'HORROR', 'SF') NOT NULL,
                       runtime_minutes INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Theater (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Screening (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           start_time TIMESTAMP NOT NULL,
                           end_time TIMESTAMP NOT NULL,
                           movie_id BIGINT NOT NULL,
                           theater_id BIGINT NOT NULL,
                           FOREIGN KEY (movie_id) REFERENCES Movie(id) ON DELETE CASCADE,
                           FOREIGN KEY (theater_id) REFERENCES Theater(id) ON DELETE CASCADE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Seat (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      seat_number INT NOT NULL,
                      screening_id BIGINT NOT NULL,
                      FOREIGN KEY (screening_id) REFERENCES Screening(id) ON DELETE CASCADE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        phone_number VARCHAR(20) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Reservation (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             reserved_at TIMESTAMP NOT NULL,
                             member_id BIGINT NOT NULL,
                             screening_id BIGINT NOT NULL,
                             status ENUM('RESERVED', 'CANCELED', 'REFUNDED') NOT NULL,
                             FOREIGN KEY (member_id) REFERENCES Member(id) ON DELETE CASCADE,
                             FOREIGN KEY (screening_id) REFERENCES Screening(id) ON DELETE CASCADE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Reservation_Seat (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  reservation_id BIGINT NOT NULL,
                                  seat_id BIGINT NOT NULL,
                                  FOREIGN KEY (reservation_id) REFERENCES Reservation(id) ON DELETE CASCADE,
                                  FOREIGN KEY (seat_id) REFERENCES Seat(id) ON DELETE CASCADE,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL

);
