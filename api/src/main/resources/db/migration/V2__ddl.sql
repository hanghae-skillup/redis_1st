
ALTER TABLE Seat DROP FOREIGN KEY seat_ibfk_1;

ALTER TABLE Seat
    DROP COLUMN seat_number,
    DROP COLUMN screening_id,
    ADD COLUMN seat_row VARCHAR(255) NOT NULL,
    ADD COLUMN seat_col VARCHAR(255) NOT NULL,
    ADD COLUMN status ENUM('RESERVED', 'CANCELED', 'REFUNDED') NOT NULL,
    ADD COLUMN theater_id BIGINT NOT NULL;

-- 3. 외래 키 수정
ALTER TABLE Seat
    ADD CONSTRAINT fk_seat_theater FOREIGN KEY (theater_id) REFERENCES Theater(id) ON DELETE CASCADE;



