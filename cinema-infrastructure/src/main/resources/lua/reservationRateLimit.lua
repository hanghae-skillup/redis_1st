-- 예약 제한 (유저 기준)
local key = KEYS[1]  -- 예약 키 (scheduleId + memberId)
local lockTime = tonumber(ARGV[1])  -- 예약 제한 시간 (초)

-- 이미 예약된 경우 차단
if redis.call("exists", key) == 1 then return 0 end

-- 예약 가능 시 만료 시간 설정
redis.call("setex", key, lockTime, "RESERVED")
return 1