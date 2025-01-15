﻿CREATE TABLE movie (
	movie_id	INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '영화 ID',
	file_id	INT UNSIGNED	NULL	COMMENT '파일 ID (썸네일)',
	title	varchar(30)	NOT NULL COMMENT '영화 제목',
	rating	varchar(2)	NOT NULL	DEFAULT 'A'	COMMENT '영상물 등급(enum 사용)',
	release_date	date	NULL COMMENT '개봉일',
	running_time_minutes	INT UNSIGNED	NOT NULL	DEFAULT 0 COMMENT '러닝타임(분)',
	genre	varchar(2)	NOT NULL	DEFAULT '1' COMMENT '영화 장르(enum 사용)',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE screen (
	screen_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '상영관 ID',
	screen_name	varchar(20)	NULL COMMENT '상영관 이름',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE screening_schedule (
	schedule_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '상영시간표 ID',
	movie_id	INT UNSIGNED	NOT NULL COMMENT '영화 ID',
	screen_id	INT UNSIGNED	NOT NULL COMMENT '싱영관 ID',
	show_start_at	datetime	NULL COMMENT '상영시작시간',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE member (
	member_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '회원 ID',
	birth_date	date	NOT NULL COMMENT '생년월일',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE ticket_reservation (
	ticket_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '영화예매 ID',
	schedule_id	INT UNSIGNED	NOT NULL COMMENT '상영시간표 ID',
	seat_id	INT UNSIGNED	NOT NULL COMMENT '좌석 ID',
	member_id	INT UNSIGNED	NOT NULL COMMENT '회원 ID',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE screen_seat (
	seat_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '좌석 ID',
	seat_name	varchar(2)	NULL COMMENT '좌석 이름',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);

CREATE TABLE upload_file (
	file_id	INT UNSIGNED	AUTO_INCREMENT PRIMARY KEY COMMENT '파일 ID',
	file_path	varchar(100)	NULL COMMENT '파일 경로',
	file_name	varchar(50)	NULL COMMENT '파일 이름',
	origin_file_name	varchar(50)	NULL COMMENT '원본 파일 이름',
	created_by	INT UNSIGNED	NULL COMMENT '작성자',
	created_at	datetime	NULL COMMENT '작성일',
	updated_by	INT UNSIGNED	NULL COMMENT '수정자',
	updated_at	datetime	NULL COMMENT '수정일'
);
