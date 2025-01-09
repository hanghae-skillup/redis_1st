# Data Module

이 모듈은 data persistence를 담당하는 계층입니다.

## 책임
- 데이터베이스 연동
- JPA 엔티티 관리
- 리포지토리 구현
- 도메인 모델과 엔티티 간 매핑

## 주요 패키지 구조
data  
├── config　　　　# JPA 및 데이터베이스 설정  
├── entity　　　 　# JPA 엔티티  
├── mapper　　　# 엔티티-도메인 매퍼  
└── repository　 # 리포지토리 구현체  

## 의존성
- core 모듈
- Spring Data JPA
- MySQL

## 주요 컴포넌트
1. 엔티티
   - MovieEntity: 영화 정보 엔티티
   - TheaterEntity: 상영관 정보 엔티티
   - ScreeningEntity: 상영 일정 정보 엔티티
   - SeatEntity: 좌석 정보 엔티티
   - UserEntity: 고객 정보 엔티티
   - ReservationEntity: 예매 정보 엔티티

2. 리포지토리
   - JpaRepository 인터페이스 구현
   - core 모듈의 리포지토리 인터페이스 구현

3. 매퍼
   - 엔티티와 도메인 모델 간 변환 담당
   - MapStruct 사용

## 규칙
1. 엔티티는 이 모듈에서만 사용
2. 데이터베이스 관련 로직은 이 모듈에서만 처리