# [본 과정] 이커머스 핵심 프로세스 구현

## How to use

```bash
docker compose up -d
```
```bash
curl -X GET http://localhost:8080/api/movies?showDate=2022-05-18
curl -X GET http://localhost:8080/api/movies?showDate=2022-05-17
```

## Multi Module
총 3개 모듈로 구성되있습니다.

### 1. movie-api
> 영화 도메인을 담당하고 Presentation Layer를 담당하는 모듈입니다.
- GET /api/movies?showDate=2025-01-01

```json
// 응답 예시
[
  {
    "id": 0,
    "title": "나 홀로 집에",
    "description": "...",
    "rating": "전체관람가",
    "genre": "코미디",
    "thumbnail": "https://...",
    "runningTime": 103,
    "releaseDate": "1991-07-06",
    "theaters": ["강남점", "안양점"],
    "showtimes": ["08:00 ~ 09:45", "10:00 ~ 11:45"]
  }
]
```
### 2. core
> 공통으로 사용하는 Entity, DTO를 담당하는 모듈입니다.
### 3. infrastructure
> DB 인터페이스를 담당하는 모듈입니다. 
> MySQL, Redis 연결을 담당하고 데이터 입출력 로직을 포함합니다. 
> DB가 변경되어도 api, core 모듈의 코드는 최소로 변경합니다.

## Architecture
> 다른 도메인 확장성을 고려한 설계입니다.
> 모든 api 모듈에서는 Entity -> DTO로 변환하여 리턴합니다.

![arc](readme/arc.png)

## Table Design
![erd_db](readme/erd.png)