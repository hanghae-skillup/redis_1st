INSERT INTO theater (`name`, `create_at`, `create_by`)
WITH RECURSIVE seq AS
(
  SELECT 0 AS num
  UNION ALL
  SELECT num + 1 as num
  from seq
  where num < 19
)
SELECT
    CONCAT('Theater_', CHAR(65 + seq.num)) as `name`,
    NOW() as `create_at`,
    'admin' as `create_by`
FROM seq;

INSERT INTO seat(`theater_id`, `seat_row`, `seat_col`, `create_at`, `create_by`)
SELECT
	t.theater_id,
	r.seat_row,
	c.seat_col,
	NOW() as `create_at`,
	'admin' as `create_by`
FROM
	theater t,
(SELECT CHAR(65 + n) as seat_row FROM (
	SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
) rr) r,
(SELECT n as seat_col FROM (
	SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
) cc) c;
