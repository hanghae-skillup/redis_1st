--[[ redis 디버깅 로그 출력
redis.log(redis.LOG_NOTICE, "maxRequests: " .. tostring(ARGV[1]))
redis.log(redis.LOG_NOTICE, "expireTime: " .. tostring(ARGV[2]))
redis.log(redis.LOG_NOTICE, "blockTime: " .. tostring(ARGV[3]))
]]

--[[ 요청 제한 (IP 기반) ]]
local key = KEYS[1]  --[[ 요청 카운트 키 ]]
local blockKey = KEYS[2]  --[[ 차단된 IP 키 ]]
local maxRequests = tonumber(ARGV[1])  --[[ 최대 요청 횟수 (초) ]]
local expireTime = tonumber(ARGV[2])  --[[ 요청 카운트 만료 시간 (초) ]]
local blockTime = tonumber(ARGV[3])  --[[ 차단 시간 (초) ]]

--[[ 차단 여부 확인 ]]
if redis.call("exists", blockKey) == 1 then return 0 end

--[[ 요청 카운트 증가 및 만료 시간 설정 ]]
local count = redis.call("incr", key)
if count == 1 then redis.call("expire", key, expireTime) end

--[[ 요청 제한 초과 시 차단 ]]
if count > maxRequests then
    redis.call("setex", blockKey, blockTime, "BLOCKED")
    return 0
end

return 1