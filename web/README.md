# Web Module

이 모듈은 HTTP API를 제공하는 presentation layer 입니다.

## 책임
- REST API 엔드포인트 제공
- 요청/응답 DTO 관리
- 입력 유효성 검증
- 예외 처리
- API 문서화

## 주요 패키지 구조
web  
├── config 　　　　# 웹 관련 설정  
├── controller　　 # REST 컨트롤러  
├── dto　　　　　 # 요청/응답 DTO  
│ ├── request  
│ └── response  
└── exception　 # 예외 처리

## 의존성
- core 모듈
- data 모듈
- Spring Web MVC
- Spring Validation

## API 엔드포인트
1. 영화 API
   - GET /api/movies: 영화 목록 조회
   - GET /api/movies/{id}: 영화 상세 조회

2. 예매 API
   - POST /api/reservations: 예매하기
   - GET /api/reservations/{id}: 예매 조회

## 설계 철학
1. 컨트롤러는 유스케이스만 호출
2. DTO와 도메인 모델 간 변환 책임
