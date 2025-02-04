-- genre 테이블 생성
create table genre
(
    genre_id   binary(16)   not null primary key,
    genre_name varchar(255) not null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    created_by binary(16)   null,
    updated_by binary(16)   null
) engine = innodb
  charset = utf8mb4;

-- movie 테이블 생성
create table movie
(
    movie_id         binary(16)   not null primary key,
    title            varchar(255) not null,
    rating           varchar(255) not null,
    release_date     date         not null,
    thumbnail_url    text,
    running_min_time int          not null,
    genre_id         binary(16)   not null,
    created_at       datetime(6)  null,
    updated_at       datetime(6)  null,
    created_by       binary(16)   null,
    updated_by       binary(16)   null,
    constraint fk_movie_genre foreign key (genre_id) references genre (genre_id)
) engine = innodb
  charset = utf8mb4;


-- cinema 테이블 생성
create table cinema
(
    cinema_id   binary(16)   not null primary key,
    cinema_name varchar(255) not null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    created_by  binary(16)   null,
    updated_by  binary(16)   null
) engine = innodb
  charset = utf8mb4;


-- screening 테이블 생성
create table screening
(
    screening_id         binary(16)  not null primary key,
    screening_start_time datetime(6) not null,
    screening_end_time   datetime(6) not null,
    movie_id             binary(16)  not null,
    cinema_id            binary(16)  not null,
    created_at           datetime(6) null,
    updated_at           datetime(6) null,
    created_by           binary(16)  null,
    updated_by           binary(16)  null,
    constraint fk_screening_movie foreign key (movie_id) references movie (movie_id),
    constraint fk_screening_cinema foreign key (cinema_id) references cinema (cinema_id)
) engine = innodb
  charset = utf8mb4;


-- seat 테이블 생성
create table seat
(
    seat_id     binary(16)   not null primary key,
    seat_number varchar(255) not null,
    cinema_id   binary(16)   not null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    created_by  binary(16)   null,
    updated_by  binary(16)   null,
    constraint fk_seat_cinema foreign key (cinema_id) references cinema (cinema_id)
) engine = innodb
  charset = utf8mb4;