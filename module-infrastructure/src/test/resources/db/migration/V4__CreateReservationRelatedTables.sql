create table reservation
(
    reservation_id   binary(16) not null primary key,
    username         varchar(255) not null,
    screening_id     binary(16) not null,
    reservation_time datetime(6) not null,
    created_at       datetime(6) null,
    updated_at       datetime(6) null,
    created_by       binary(16) null,
    updated_by       binary(16) null
) engine=innodb charset=utf8mb4;


create table reservation_seat
(
    reservation_seat_id binary(16) not null primary key,
    reservation_id      binary(16) not null,
    screening_id        binary(16) not null,
    seat_id             binary(16) not null,
    created_at          datetime(6) null,
    updated_at          datetime(6) null,
    created_by          binary(16) null,
    updated_by          binary(16) null,
    constraint UK_screening_seat unique (screening_id, seat_id)
) engine=innodb charset=utf8mb4;