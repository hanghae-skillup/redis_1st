INSERT INTO reserve (reserve_receipt_id, screening_id, seat_id, user_id, create_at)
SELECT
    null AS reserve_receipt_id,
    s.screening_id,
    se.seat_id,
    NULL AS user_id,
    NOW() AS create_at
FROM
    screening s
JOIN
    seat se ON se.theater_id = s.theater_id;
