# 성능 테스트 보고서

# 전제 조건

- **MAU(월간 활성 사용자)**: 3,800,000명 (CGV 기준)
- **평일 DAU**: MAU의 4% = 152,000명
- **주말 DAU**: MAU의 12% = 456,000명
- **피크 시간대 DAU**: 주말 DAU의 2.5배 = 1,140,000명
- **1인당 평균 세션 수**: 2.5회
- **Throughput 계산**:
    - **평일 총 세션 수** = 평일 DAU × 평균 세션 수 = 152,000 × 2.5 = 380,000
    - **주말 총 세션 수** = 주말 DAU × 평균 세션 수 = 456,000 × 2.5 = 1,140,000
    - **피크 총 세션 수** = 피크 DAU × 평균 세션 수 = 1,140,000 × 2.5 = 2,850,000
    - **평일 평균 RPS** = 평일 총 세션 수 ÷ 86,400 (초/일) ≈ 4.4 RPS
    - **주말 평균 RPS** = 주말 총 세션 수 ÷ 86,400 (초/일) ≈ 13.2 RPS
    - **피크 시간 RPS** = 피크 총 세션 수 ÷ 86,400 (초/일) ≈ 33 RPS

- **테스트 시나리오**:
    - **평일 시나리오**: 
        - 1분: 0 → 100 VUs (워밍업)
        - 3분: 100 → 500 VUs (점진적 증가)
        - 2분: 500 VUs 유지 (부하 유지)
        - 1분: 500 → 0 VUs (정리)
    - **주말 시나리오**:
        - 5분: 0 → WEEKEND_DAU × 0.1 VUs
        - 10분: WEEKEND_DAU × 0.3 VUs 유지
        - 5분: → 0 VUs
    - **피크 시나리오**:
        - 5분: 0 → PEAK_DAU × 0.1 VUs
        - 10분: PEAK_DAU × 0.3 VUs 유지
        - 5분: → 0 VUs

- optional
    - thresholds
        - e.g p(95) 의 응답 소요 시간 200ms 이하
        - 실패율 1% 이하

# 0. N+1 문제 해결 전 / 후

## N+1 문제 해결 전
- 1주차 시나리오 테스트만 먼저 진행하려다 보니 N+1 문제를 해결하지 않고 부하 테스트를 진행해 버렸습니다.
- N+1 문제가 해결되지 않아 영화 `SELECT` 쿼리 이후 극장 및 상영 스케줄을 조회하는 쿼리를 첨부합니다.
- 이 쿼리의 실행 계획은 시간이 되면 추가해서 수정해 두겠습니다.

### 쿼리 (실제로 동작하는 쿼리)
```sql
select
    distinct se1_0.id,
    se1_0.created_at,
    se1_0.created_by,
    se1_0.movie_id,
    se1_0.start_time,
    se1_0.theater_id,
    t1_0.id,
    t1_0.name,
    se1_0.updated_at,
    se1_0.updated_by 
from
    screening se1_0 
left join
    theater t1_0 
        on t1_0.id=se1_0.theater_id 
where
    se1_0.movie_id=? 
order by
    se1_0.start_time
```
### 실행 계획(추가 예정)

### 부하 테스트 결과 (스크린샷)
![1주차 부하 테스트 중](https://github.com/jjh94210/redis_1st/raw/main/web/src/test/img/1%EC%A3%BC%EC%B0%A8%20API%20%EB%B6%80%ED%95%98%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%A7%84%ED%96%89%20%EC%A4%91.png)  
![1주차 API 부하 테스트 결과](https://github.com/jjh94210/redis_1st/raw/main/web/src/test/img/1%EC%A3%BC%EC%B0%A8%20API%20%EB%B6%80%ED%95%98%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EA%B2%B0%EA%B3%BC.png)
![1주차 단일 API 실행 결과](https://github.com/jjh94210/redis_1st/raw/main/web/src/test/img/1%EC%A3%BC%EC%B0%A8%20%EB%8B%A8%EC%9D%BC%20API%20%EC%8B%A4%ED%96%89%20%EA%B2%B0%EA%B3%BC.png)

## N+1 문제 해결 후

### 쿼리 (실제로 동작하는 쿼리)
```sql
select
    distinct me1_0.id,
    me1_0.created_at,
    me1_0.created_by,
    me1_0.genre,
    me1_0.rating,
    me1_0.release_date,
    me1_0.running_time,
    s1_0.movie_id,
    s1_0.id,
    s1_0.created_at,
    s1_0.created_by,
    s1_0.start_time,
    s1_0.theater_id,
    t1_0.id,
    t1_0.name,
    s1_0.updated_at,
    s1_0.updated_by,
    me1_0.thumbnail_url,
    me1_0.title,
    me1_0.updated_at,
    me1_0.updated_by 
from
    movie me1_0 
left join
    screening s1_0 
        on me1_0.id=s1_0.movie_id 
left join
    theater t1_0 
        on t1_0.id=s1_0.theater_id 
order by
    me1_0.release_date desc
```

### 실행 계획

### 부하 테스트 결과 (스크린샷)
![1주차 N+1 문제 해결 후 API 부하 테스트 결과](https://github.com/jjh94210/redis_1st/raw/main/web/src/test/img/1%EC%A3%BC%EC%B0%A8%20N%2B1%20%EB%AC%B8%EC%A0%9C%20%ED%95%B4%EA%B2%B0%20%ED%9B%84%20API%20%EB%B6%80%ED%95%98%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EA%B2%B0%EA%B3%BC.png)  
![1주차 N+1 문제 해결 후 단일 API 실행 결과](https://github.com/jjh94210/redis_1st/raw/main/web/src/test/img/1%EC%A3%BC%EC%B0%A8%20N%2B1%20%EB%AC%B8%EC%A0%9C%20%ED%95%B4%EA%B2%B0%20%ED%9B%84%20%EB%8B%A8%EC%9D%BC%20API%20%EC%8B%A4%ED%96%89%20%EA%B2%B0%EA%B3%BC.png)

# 1. Index 적용 전

### 쿼리 (실제로 동작하는 쿼리)

### 실행 계획

### 부하 테스트 결과 (스크린샷)

# 2. Index 적용 후

### 적용한 인덱스 DDL

### 쿼리 (실제로 동작하는 쿼리)

### 실행 계획

### 부하 테스트 결과 (스크린샷)

# 3. 로컬 Caching 적용 후

### 캐싱한 데이터의 종류

### 실행 계획

### 부하 테스트 결과 (스크린샷)

# 4. 분산 Caching 적용 후

### 캐싱한 데이터의 종류

### 실행 계획

### 부하 테스트 결과 (스크린샷)
