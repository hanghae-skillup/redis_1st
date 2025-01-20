# project-redis
<br>


## 1. Common 모듈 
### **목적**
- 프로젝트 전체에서 공통적으로 사용되는 기능 및 클래스 관리.
- 전역적으로 사용되는 예외 처리와 공통 유틸리티 제공.

### **역할**
- 전역 예외 정의
- 공통 유틸리티 클래스 (예: 날짜 포맷, 문자열 처리)
- 프로젝트 전역 상수 및 설정

---

## 2. API 모듈 
### **목적**
- 외부 세계와의 직접적인 통신을 담당함.
- 주로 컨트롤러 레이어와 관련된 역할 수행.

### **역할**
- **실행 모듈**
- HTTP 요청 처리
- REST API 엔드포인트 제공
- Spring Boot 애플리케이션 시작점

---

## 3. Application 모듈 
### **목적**
- 비즈니스 로직을 처리하는 서비스 레이어.
- 도메인 로직과 외부 인터페이스 간의 연결을 담당.

### **역할**
- 비즈니스 서비스 구현
- 포트(Port) 인터페이스 정의
- 도메인 객체와 외부 인터페이스 간 데이터 변환 및 조율

---

## 4. Domain 모듈 
### **목적**
- 프로젝트 도메인 및 엔티티 관리.
- 애플리케이션의 주요 비즈니스 규칙을 정의.

### **역할**
- 도메인 객체 정의
- 도메인 서비스 및 비즈니스 규칙 검증
- 도메인 객체 간 관계 설정

---

## 5. Infrastructure 모듈 
### **목적**
- 데이터베이스 및 JPA 관련 설정과 구현을 담당.
- 외부 시스템(데이터베이스, 메시징 등)과의 상호작용 관리.

### **역할**
- 엔티티 관리
- 더미 데이터 생성
- JPA 레포지토리 인터페이스 정의 및 구현
- 데이터베이스 설정
- 외부 시스템 통합 (예: 캐시, 메시징)

---

## 모듈 구조 요약

```plaintext
project-movie/
├── common/         
├── api/           
├── application/    
├── domain/         
└── infrastructure/ 
```
---

## 사용 방법
```
docker compose up -d
```

#### 조회하는 날짜를 기준으로 상영 중인 영화 상세 조회 및 검색
- 상영관: 50개
- 영화: 500개
- 상영시간: 10,000개
```
curl -x GET http://localhost:8080/api/v3/movie
```
```
curl -x GET http://localhost:8080/api/v3/movie?title=5
```
```
curl -x GET http://localhost:8080/api/v3/movie?title=9&genre=ANIMATION
```

또는, <br>
> api/src/test/java/hanghae/api/adapter/MovieController.http 실행



#### 응답 예시
_endTime: 편의를 위해 runningTime 과 관계없이 startTime 의 2시간 뒤로 고정_
```JSON
[
  {
    "title": "Movie #1",
    "ageRating": "AGE_19",
    "releaseDate": "2024-10-13",
    "thumbnailUrl": "https://www.dummy-url/Movie #1",
    "runningTime": 141,
    "genre": "ACTION",
    "showtime": [
      {
        "startTime": "20250206:11:00",
        "endTime": "20250206:13:00",
        "theatersName": [
          "Theater #47"
        ]
      },
      {
        "startTime": "20250727:10:22",
        "endTime": "20250727:12:22",
        "theatersName": [
          "Theater #22",
          "Theater #11"
        ]
      },
      ...
    ]
  },
  {
    "title": "Movie #7",
    "ageRating": "AGE_12",
    "releaseDate": "2023-04-25",
    "thumbnailUrl": "https://www.dummy-url/Movie #7",
    "runningTime": 120,
    "genre": "ANIMATION",
    "showtime": [
      {
        "startTime": "20250121:07:09",
        "endTime": "20250121:09:09",
        "theatersName": [
          "Theater #32",
          "Theater #33"
        ]
      },
      {
        "startTime": "20251118:00:23",
        "endTime": "20251118:02:23",
        "theatersName": [
          "Theater #27",
          "Theater #1"
        ]
      },
      ...
    ]
  },
  ...
]
```

---

## ERD
![project-redis](https://github.com/user-attachments/assets/e63df86b-db9b-4fe5-adaf-e4e41b61269f)
