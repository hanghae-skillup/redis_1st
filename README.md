## [본 과정] 이커머스 핵심 프로세스 구현

```bash
docker compose up -d
```

```bash
curl -X GET http://localhost:8080/api/v1/movies
```

### Multi Module
1. adapter
> Presentation layer
2. application
> UseCase layer
3. infrastructure
> DB 연결 및 Entity 클래스 관리
4. core
> Domain layer
