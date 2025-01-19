create table movie
(
    id           int unsigned auto_increment comment '아이디' primary key,
    created_at   datetime(6)                                                                               not null comment '생성 일시',
    updated_at   datetime(6)                                                                               null comment '수정 일시',
    age_rating   enum ('ALL', 'FIFTEEN', 'NINETEEN', 'TWELVE')                                             not null comment '등급',
    category     enum ('ACTION', 'COMEDY', 'DRAMA', 'FANTASY', 'HORROR', 'MYSTERY', 'ROMANCE', 'THRILLER') not null comment '장르',
    durationMin     int                                                                                       not null comment '상영 시간(min)',
    release_date date                                                                                      not null comment '개봉일',
    theater      enum ('ROOM_1', 'ROOM_2', 'ROOM_3', 'ROOM_4', 'ROOM_5', 'ROOM_6')                         not null comment '상영관',
    title        varchar(50)                                                                               not null comment '제목',
    thumbnail_id int unsigned                                                                              not null,
    constraint uk_movie_movie_thumbnail_id unique (thumbnail_id)
) comment '영화';

create table movie_thumbnail
(
    id         int unsigned auto_increment comment '아이디' primary key,
    created_at datetime(6)  not null comment '생성 일시',
    updated_at datetime(6)  null comment '수정 일시',
    path       varchar(255) not null comment '경로',
    url        varchar(255) not null comment 'URL',
    movie_id   int unsigned not null,
    constraint uk_movie_movie_thumbnail_id unique (movie_id)
) comment '영화 썸네일';

create table schedule
(
    id         int unsigned auto_increment comment '아이디' primary key,
    created_at datetime(6)  not null comment '생성 일시',
    updated_at datetime(6)  null comment '수정 일시',
    end_at     time(6)      not null comment '상영 종료 시간',
    start_at   time(6)      not null comment '상영 시작 시간',
    movie_id   int unsigned not null
) comment '상영 시간표';
create index idx_schedule_movie_id on schedule (movie_id);

create table ticket
(
    id          int unsigned auto_increment comment '아이디' primary key,
    created_at  datetime(6)                              not null comment '생성 일시',
    updated_at  datetime(6)                              null comment '수정 일시',
    seats_col   enum ('A', 'B', 'C', 'D', 'E')           not null comment '좌석 열',
    seats_row   enum ('R_1', 'R_2', 'R_3', 'R_4', 'R_5') not null comment '좌석 행',
    schedule_id int unsigned                             not null
) comment '티켓';
create index idx_ticket_schedule_id on ticket (schedule_id);
