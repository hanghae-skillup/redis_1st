create table movie.movie
(
    id           int unsigned auto_increment comment '아이디' primary key,
    created_at   datetime(6)                                                                               not null comment '생성 일시',
    updated_at   datetime(6)                                                                               null comment '수정 일시',
    age_rating   enum ('ALL', 'FIFTEEN', 'NINETEEN', 'TWELVE')                                             null comment '등급',
    category     enum ('ACTION', 'COMEDY', 'DRAMA', 'FANTASY', 'HORROR', 'MYSTERY', 'ROMANCE', 'THRILLER') null comment '장르',
    duration     int                                                                                       null comment '상영 시간(min)',
    release_date date                                                                                      null comment '개봉일',
    theater      enum ('ROOM_1', 'ROOM_2', 'ROOM_3', 'ROOM_4', 'ROOM_5', 'ROOM_6')                         null comment '상영관',
    title        varchar(255)                                                                              null comment '제목'
) comment '영화';

create table movie.movie_thumbnail
(
    id         int unsigned auto_increment comment '아이디' primary key,
    created_at datetime(6)  not null comment '생성 일시',
    updated_at datetime(6)  null comment '수정 일시',
    path       varchar(255) null comment '경로',
    url        varchar(255) null comment 'URL',
    movie_id   int unsigned       not null,
    constraint UK_movie_thumbnail_move_id unique (movie_id),
    constraint FK_movie_thumbnail_movie_id foreign key (movie_id) references movie.movie (id)
) comment '영화 이미지';

create table movie.schedule
(
    id         int unsigned auto_increment comment '아이디' primary key,
    created_at datetime(6) not null comment '생성 일시',
    updated_at datetime(6) null comment '수정 일시',
    end_at     time(6)     null comment '상영 종료 시간',
    start_at   time(6)     null comment '상영 시작 시간',
    movie_id   int unsigned      null,
    constraint FK_schedule_movie_id foreign key (movie_id) references movie.movie (id)
) comment '상영 시간표';

create table movie.ticket
(
    id          int unsigned auto_increment comment '아이디' primary key,
    created_at  datetime(6)                              not null comment '생성 일시',
    updated_at  datetime(6)                              null comment '수정 일시',
    seats_col   enum ('A', 'B', 'C', 'D', 'E')           null comment '좌석 열',
    seats_row   enum ('R_1', 'R_2', 'R_3', 'R_4', 'R_5') null comment '좌석 행',
    schedule_id int unsigned                                   null,
    constraint FK_ticket_schedule_id foreign key (schedule_id) references movie.schedule (id)
) comment '티켓';

