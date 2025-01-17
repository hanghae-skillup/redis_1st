-- Genre 데이터 (6개)
INSERT INTO genre (name, description, created_by, created_at, updated_by, updated_at) VALUES
      ('액션', '스릴 넘치는 액션 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('코미디', '웃음이 가득한 코미디 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('로맨스', '달달한 로맨스 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('공포', '무서운 공포 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('SF', '미래와 상상을 담은 SF 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('드라마', '감동적인 드라마 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Theater 데이터 (5개)
INSERT INTO theater (name, created_by, created_at, updated_by, updated_at) VALUES
    ('1관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('2관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('3관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('4관 - PREMIUM', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('5관 - SWEET', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('6관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('7관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('8관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('9관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('10관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('11관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('12관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('13관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('14관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('15관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('16관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('17관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('18관 - PREMIUM', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('19관 - SWEET', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('20관 - SWEET', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('21관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('22관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('23관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('24관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('25관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('26관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('27관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('28관 - PREMIUM', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('29관 - SWEET', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('30관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Movie 데이터
INSERT INTO movie (title, rating, genre_id, release_date, thumbnail_url, running_time, created_by, created_at, updated_by, updated_at)
VALUES
-- 액션 (genre_id: 1)
('어벤져스: 엔드게임', 'TWELVE', 1, '2019-04-24', 'http://example.com/avengers.jpg', 181, 'system', NOW(), 'system', NOW()),
('인셉션', 'TWELVE', 1, '2010-07-21', 'http://example.com/inception.jpg', 148, 'system', NOW(), 'system', NOW()),
('매트릭스', 'FIFTEEN', 1, '1999-05-15', 'http://example.com/matrix.jpg', 136, 'system', NOW(), 'system', NOW()),
('다크 나이트', 'FIFTEEN', 1, '2008-08-06', 'http://example.com/dark-knight.jpg', 152, 'system', NOW(), 'system', NOW()),
('미션 임파서블', 'TWELVE', 1, '1996-05-25', 'http://example.com/mission-impossible.jpg', 110, 'system', NOW(), 'system', NOW()),

-- 코미디 (genre_id: 2)
('극한직업', 'FIFTEEN', 2, '2019-01-23', 'http://example.com/extreme-job.jpg', 111, 'system', NOW(), 'system', NOW()),
('죽이는 여자', 'FIFTEEN', 2, '2024-02-14', 'http://example.com/kill-boksoon.jpg', 137, 'system', NOW(), 'system', NOW()),
('써니', 'FIFTEEN', 2, '2011-05-04', 'http://example.com/sunny.jpg', 124, 'system', NOW(), 'system', NOW()),
('과속스캔들', 'ALL', 2, '2008-12-03', 'http://example.com/scandal-makers.jpg', 108, 'system', NOW(), 'system', NOW()),
('7번방의 선물', 'TWELVE', 2, '2013-01-23', 'http://example.com/miracle.jpg', 127, 'system', NOW(), 'system', NOW()),

-- 로맨스 (genre_id: 3)
('타이타닉', 'TWELVE', 3, '1998-02-20', 'http://example.com/titanic.jpg', 194, 'system', NOW(), 'system', NOW()),
('노트북', 'TWELVE', 3, '2004-06-25', 'http://example.com/notebook.jpg', 123, 'system', NOW(), 'system', NOW()),
('이터널 선샤인', 'TWELVE', 3, '2005-11-10', 'http://example.com/eternal-sunshine.jpg', 108, 'system', NOW(), 'system', NOW()),
('러브 액츄얼리', 'FIFTEEN', 3, '2003-12-05', 'http://example.com/love-actually.jpg', 135, 'system', NOW(), 'system', NOW()),
('어바웃 타임', 'FIFTEEN', 3, '2013-09-04', 'http://example.com/about-time.jpg', 123, 'system', NOW(), 'system', NOW()),

-- 공포 (genre_id: 4) 시작
('쏘우', 'ADULT', 4, '2004-10-29', 'http://example.com/saw.jpg', 103, 'system', NOW(), 'system', NOW()),
('컨저링', 'FIFTEEN', 4, '2013-09-17', 'http://example.com/conjuring.jpg', 112, 'system', NOW(), 'system', NOW()),
('링', 'FIFTEEN', 4, '1999-01-31', 'http://example.com/ring.jpg', 96, 'system', NOW(), 'system', NOW()),
('28일 후', 'ADULT', 4, '2003-05-01', 'http://example.com/28-days-later.jpg', 113, 'system', NOW(), 'system', NOW()),
('할로윈', 'ADULT', 4, '1978-10-25', 'http://example.com/halloween.jpg', 91, 'system', NOW(), 'system', NOW()),

-- SF (genre_id: 5)
('인터스텔라', 'TWELVE', 5, '2014-11-06', 'http://example.com/interstellar.jpg', 169, 'system', NOW(), 'system', NOW()),
('그래비티', 'TWELVE', 5, '2013-10-02', 'http://example.com/gravity.jpg', 91, 'system', NOW(), 'system', NOW()),
('마션', 'TWELVE', 5, '2015-10-08', 'http://example.com/martian.jpg', 144, 'system', NOW(), 'system', NOW()),
('엣지 오브 투모로우', 'FIFTEEN', 5, '2014-06-04', 'http://example.com/edge-of-tomorrow.jpg', 113, 'system', NOW(), 'system', NOW()),
('아이 엠 레전드', 'FIFTEEN', 5, '2007-12-14', 'http://example.com/i-am-legend.jpg', 101, 'system', NOW(), 'system', NOW()),

-- 드라마 (genre_id: 6)
('쇼생크 탈출', 'FIFTEEN', 6, '1995-01-28', 'http://example.com/shawshank.jpg', 142, 'system', NOW(), 'system', NOW()),
('포레스트 검프', 'TWELVE', 6, '1994-10-15', 'http://example.com/forrest-gump.jpg', 142, 'system', NOW(), 'system', NOW()),
('위플래시', 'FIFTEEN', 6, '2015-02-05', 'http://example.com/whiplash.jpg', 107, 'system', NOW(), 'system', NOW()),
('굿 윌 헌팅', 'FIFTEEN', 6, '1998-03-14', 'http://example.com/good-will-hunting.jpg', 126, 'system', NOW(), 'system', NOW()),
('뷰티풀 마인드', 'TWELVE', 6, '2002-02-22', 'http://example.com/beautiful-mind.jpg', 135, 'system', NOW(), 'system', NOW()),

-- 액션 (genre_id: 1) 계속
('다이하드', 'FIFTEEN', 1, '1988-07-20', 'http://example.com/die-hard.jpg', 132, 'system', NOW(), 'system', NOW()),
('글래디에이터', 'FIFTEEN', 1, '2000-05-05', 'http://example.com/gladiator.jpg', 155, 'system', NOW(), 'system', NOW()),
('본 얼티메이텀', 'FIFTEEN', 1, '2007-08-23', 'http://example.com/bourne.jpg', 115, 'system', NOW(), 'system', NOW()),
('매드 맥스: 분노의 도로', 'FIFTEEN', 1, '2015-05-14', 'http://example.com/mad-max.jpg', 120, 'system', NOW(), 'system', NOW()),
('존 윅', 'ADULT', 1, '2014-10-24', 'http://example.com/john-wick.jpg', 101, 'system', NOW(), 'system', NOW()),
('킹스맨: 시크릿 에이전트', 'ADULT', 1, '2015-02-11', 'http://example.com/kingsman.jpg', 129, 'system', NOW(), 'system', NOW()),
('토르: 라그나로크', 'TWELVE', 1, '2017-10-25', 'http://example.com/thor.jpg', 130, 'system', NOW(), 'system', NOW()),
('탑건: 매버릭', 'TWELVE', 1, '2022-06-22', 'http://example.com/top-gun.jpg', 130, 'system', NOW(), 'system', NOW()),

-- 코미디 (genre_id: 2) 계속
('행오버', 'ADULT', 2, '2009-06-11', 'http://example.com/hangover.jpg', 100, 'system', NOW(), 'system', NOW()),
('브루스 올마이티', 'TWELVE', 2, '2003-05-23', 'http://example.com/bruce-almighty.jpg', 101, 'system', NOW(), 'system', NOW()),
('좀비랜드', 'ADULT', 2, '2009-10-08', 'http://example.com/zombieland.jpg', 88, 'system', NOW(), 'system', NOW()),
('셰이프 오브 워터', 'ADULT', 2, '2018-02-21', 'http://example.com/shape-of-water.jpg', 123, 'system', NOW(), 'system', NOW()),
('트루먼 쇼', 'TWELVE', 2, '1998-06-05', 'http://example.com/truman-show.jpg', 103, 'system', NOW(), 'system', NOW()),
('스파이더맨: 어크로스 더 유니버스', 'ALL', 2, '2023-06-21', 'http://example.com/spiderverse.jpg', 140, 'system', NOW(), 'system', NOW()),

-- SF (genre_id: 5) 계속
('블레이드 러너 2049', 'FIFTEEN', 5, '2017-10-12', 'http://example.com/blade-runner.jpg', 163, 'system', NOW(), 'system', NOW()),
('아리바달', 'TWELVE', 5, '2016-07-20', 'http://example.com/arrival.jpg', 116, 'system', NOW(), 'system', NOW()),
('엑스 마키나', 'FIFTEEN', 5, '2015-03-23', 'http://example.com/ex-machina.jpg', 108, 'system', NOW(), 'system', NOW()),
('루퍼', 'FIFTEEN', 5, '2012-09-27', 'http://example.com/looper.jpg', 118, 'system', NOW(), 'system', NOW()),
('패신저스', 'TWELVE', 5, '2016-12-28', 'http://example.com/passengers.jpg', 116, 'system', NOW(), 'system', NOW()),
('월-E', 'ALL', 5, '2008-08-06', 'http://example.com/wall-e.jpg', 98, 'system', NOW(), 'system', NOW()),

-- 드라마 (genre_id: 6) 계속
('그린 마일', 'FIFTEEN', 6, '2000-02-19', 'http://example.com/green-mile.jpg', 189, 'system', NOW(), 'system', NOW()),
('파이트 클럽', 'ADULT', 6, '1999-11-13', 'http://example.com/fight-club.jpg', 139, 'system', NOW(), 'system', NOW()),
('인생은 아름다워', 'TWELVE', 6, '1999-03-01', 'http://example.com/life-is-beautiful.jpg', 116, 'system', NOW(), 'system', NOW()),
('레옹', 'ADULT', 6, '1995-02-04', 'http://example.com/leon.jpg', 110, 'system', NOW(), 'system', NOW()),
('메멘토', 'FIFTEEN', 6, '2001-06-29', 'http://example.com/memento.jpg', 113, 'system', NOW(), 'system', NOW());

-- 예정된 상영 (SCHEDULED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 500
)
SELECT
    MOD(n, 55) + 1,
    MOD(30, 5) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL n HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (n + 2) HOUR),
    'SCHEDULED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 과거 완료된 상영 (COMPLETED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 100
)
SELECT
    MOD(n, 55) + 1,
    MOD(n, 30) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL -n HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (-n + 2) HOUR),
    'COMPLETED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 취소된 상영 (CANCELLED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 50
)
SELECT
    MOD(n, 55) + 1,
    MOD(n, 30) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL n * 2 HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (n * 2 + 2) HOUR),
    'CANCELLED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 진행중인 상영 (IN_PROGRESS)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 10
)
SELECT
    MOD(n, 55) + 1,
    MOD(n, 30) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL -30 MINUTE),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 90 MINUTE),
    'IN_PROGRESS',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;