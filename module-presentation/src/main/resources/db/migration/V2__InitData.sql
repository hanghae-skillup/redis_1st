insert into genre (genre_id, genre_name)
values (uuid_to_bin(uuid()), '액션'),
       (uuid_to_bin(uuid()), '코미디'),
       (uuid_to_bin(uuid()), '드라마'),
       (uuid_to_bin(uuid()), '판타지'),
       (uuid_to_bin(uuid()), '로맨스');

insert into cinema (cinema_id, cinema_name)
values (uuid_to_bin(uuid()), '스타라이트 상영관'),
       (uuid_to_bin(uuid()), '드림씨어터'),
       (uuid_to_bin(uuid()), '선셋 극장'),
       (uuid_to_bin(uuid()), '루프탑 상영관'),
       (uuid_to_bin(uuid()), '클래식 상영관');

insert into seat (seat_id, seat_number, cinema_id)
values
    -- 루프탑 상영관 좌석
    (uuid_to_bin(uuid()), 'A1', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'A2', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'A3', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'A4', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'A5', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'B1', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'B2', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'B3', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'B4', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'B5', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'C1', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'C2', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'C3', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'C4', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'C5', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'D1', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'D2', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'D3', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'D4', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'D5', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'E1', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'E2', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'E3', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'E4', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),
    (uuid_to_bin(uuid()), 'E5', (select cinema_id from cinema where cinema_name = '루프탑 상영관')),

    -- 클래식 상영관 좌석
    (uuid_to_bin(uuid()), 'A1', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'A2', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'A3', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'A4', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'A5', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'B1', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'B2', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'B3', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'B4', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'B5', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'C1', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'C2', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'C3', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'C4', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'C5', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'D1', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'D2', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'D3', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'D4', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'D5', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'E1', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'E2', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'E3', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'E4', (select cinema_id from cinema where cinema_name = '클래식 상영관')),
    (uuid_to_bin(uuid()), 'E5', (select cinema_id from cinema where cinema_name = '클래식 상영관')),

    (uuid_to_bin(uuid()), 'A1', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'A2', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'A3', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'A4', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'A5', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'B1', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'B2', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'B3', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'B4', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'B5', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'C1', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'C2', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'C3', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'C4', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'C5', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'D1', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'D2', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'D3', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'D4', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'D5', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'E1', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'E2', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'E3', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'E4', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),
    (uuid_to_bin(uuid()), 'E5', (select cinema_id from cinema where cinema_name = '스타라이트 상영관')),

    (uuid_to_bin(uuid()), 'A1', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'A2', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'A3', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'A4', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'A5', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'B1', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'B2', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'B3', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'B4', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'B5', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'C1', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'C2', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'C3', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'C4', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'C5', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'D1', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'D2', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'D3', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'D4', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'D5', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'E1', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'E2', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'E3', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'E4', (select cinema_id from cinema where cinema_name = '드림씨어터')),
    (uuid_to_bin(uuid()), 'E5', (select cinema_id from cinema where cinema_name = '드림씨어터')),

    (uuid_to_bin(uuid()), 'A1', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'A2', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'A3', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'A4', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'A5', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'B1', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'B2', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'B3', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'B4', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'B5', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'C1', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'C2', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'C3', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'C4', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'C5', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'D1', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'D2', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'D3', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'D4', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'D5', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'E1', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'E2', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'E3', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'E4', (select cinema_id from cinema where cinema_name = '선셋 극장')),
    (uuid_to_bin(uuid()), 'E5', (select cinema_id from cinema where cinema_name = '선셋 극장'));


insert into movie (movie_id, title, rating, release_date, thumbnail_url, running_min_time, genre_id)
values
    -- 액션 장르 영화
    (uuid_to_bin(uuid()), '매드 맥스: 분노의 도로', 'NINETEEN', '2015-05-15', 'https://example.com/madmax.jpg', 120,
     (select genre_id from genre where genre_name = '액션')),
    (uuid_to_bin(uuid()), '다이하드', 'NINETEEN', '1988-07-20', 'https://example.com/diehard.jpg', 131,
     (select genre_id from genre where genre_name = '액션')),

    -- 코미디 장르 영화
    (uuid_to_bin(uuid()), '슈퍼배드', 'TWELVE', '2010-07-09', 'https://example.com/despicableme.jpg', 95,
     (select genre_id from genre where genre_name = '코미디')),
    (uuid_to_bin(uuid()), '트루먼 쇼', 'TWELVE', '1998-06-05', 'https://example.com/trumanshow.jpg', 103,
     (select genre_id from genre where genre_name = '코미디')),

    -- 드라마 장르 영화
    (uuid_to_bin(uuid()), '쇼생크 탈출', 'FIFTEEN', '1994-09-23', 'https://example.com/shawshank.jpg', 142,
     (select genre_id from genre where genre_name = '드라마')),
    (uuid_to_bin(uuid()), '포레스트 검프', 'TWELVE', '1994-07-06', 'https://example.com/forrestgump.jpg', 144,
     (select genre_id from genre where genre_name = '드라마')),

    -- 판타지 장르 영화
    (uuid_to_bin(uuid()), '반지의 제왕: 반지 원정대', 'FIFTEEN', '2001-12-19', 'https://example.com/lotr.jpg', 178,
     (select genre_id from genre where genre_name = '판타지')),
    (uuid_to_bin(uuid()), '해리 포터와 마법사의 돌', 'TWELVE', '2001-11-16', 'https://example.com/harrypotter.jpg', 152,
     (select genre_id from genre where genre_name = '판타지')),

    -- 로맨스 장르 영화
    (uuid_to_bin(uuid()), '타이타닉', 'FIFTEEN', '1997-12-19', 'https://example.com/titanic.jpg', 195,
     (select genre_id from genre where genre_name = '로맨스')),
    (uuid_to_bin(uuid()), '노트북', 'FIFTEEN', '2004-06-25', 'https://example.com/notebook.jpg', 123,
     (select genre_id from genre where genre_name = '로맨스'));
