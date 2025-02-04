# project-movie
<br>


## 1. API 모듈
- HTTP 요청 처리
- REST API 엔드포인트 제공
- Spring Boot 애플리케이션 시작점

---

## 2. Application 모듈
- 비즈니스 서비스 구현
- 포트(Port) 인터페이스 정의
- 도메인 객체와 외부 인터페이스 간 데이터 변환 및 조율

---

## 3. Domain 모듈
- 엔티티 정의
- 레포지토리 정의
- 엔티티 객체 관계 설정

---

## 4. Infrastructure 모듈
- 더미 데이터 생성
- JPA 레포지토리 인터페이스 정의 및 구현
- 데이터베이스 설정
- 외부 시스템 통합

---

## 모듈 구조 요약

```plaintext
project-movie/       
├── api/           
├── application/    
├── domain/         
└── infrastructure/ 
```
---

## ERD
[movie_app-erd](https://github.com/user-attachments/assets/aa4d568c-948c-4334-bae1-d29ca2602efa)

---

## 분산 락 성능 테스트(AOP)
[reserveAPI_distributed_lock_test-AOP](https://github.com/orkrj/project-movie/blob/main/reserve-distributed-AOP.png)

## 분산 락 성능 테스트(Lamda)
[reserveAPI_distributed_lock_test-Functional](https://github.com/orkrj/project-movie/blob/main/reserve-distributed-Functional.png)

## 결정
#### 1. 분산 락 구현 방식
- vus 100, duration 600s 환경에서 테스트한 결과, AOP 기반 분산 락이 MAX 응답 시간은 크지만 avg, P(90), P(95), TPS에서 더 우수한 성능을 보임.
- 따라서, 함수형 구현 대신 AOP 기반으로 분산 락을 적용하기로 결정.

#### 2. waitTime, leaseTime
- waitTime = 1s : 영화 예매의 경우 대체 선택지가 많아, 대기 시간을 짧게 설정하여 사용자 경험(UX)을 개선함. 불필요한 서버 부하 또한 예방 가능한 결정임.
- leaseTime = 3s : 분산 락 테스트 결과, 응답 시간 P(95) 68ms, 최대 응답 시간 1015ms 가 측정되어 최대 응답의 3배를 지정하여 안정성을 확보하려 함.
---