# Configuration Directory

이 디렉토리는 Spring Boot 애플리케이션의 외부 설정 파일을 관리합니다.

## 파일 구조

```
config/
├── application.yml           # 모든 애플리케이션 공통 설정
├── app-core/
│   ├── application.yml       # app-core 애플리케이션 기본 설정
│   ├── application-dev.yml   # app-core 개발 환경 설정
│   └── application-prod.yml  # app-core 운영 환경 설정
└── README.md                 # 이 문서
```

## 설정 우선순위

Spring Boot는 다음 순서로 설정을 로드하며, 나중에 로드된 설정이 이전 설정을 덮어씁니다:

1. `application.yml` - 공통 기본 설정
2. `app-core/application.yml` - 애플리케이션별 설정
3. `app-core/application-{profile}.yml` - 프로필별 설정

## 프로필 사용

### 개발 환경 실행
```bash
# 방법 1: 환경 변수 설정
export SPRING_PROFILES_ACTIVE=dev
./gradlew :app-core:bootRun

# 방법 2: JVM 옵션
./gradlew :app-core:bootRun --args='--spring.profiles.active=dev'

# 방법 3: JAR 실행 시
java -jar app-core.jar --spring.profiles.active=dev
```

### 운영 환경 실행
```bash
export SPRING_PROFILES_ACTIVE=prod
export CORE_DB_HOST=production-db.example.com
export CORE_DB_USERNAME=produser
export CORE_DB_PASSWORD=secretpassword
java -jar app-core.jar
```

## 환경 변수

운영 환경에서 민감한 정보는 환경 변수로 관리합니다:

- `CORE_DB_HOST`: 데이터베이스 호스트
- `CORE_DB_PORT`: 데이터베이스 포트
- `CORE_DB_NAME`: 데이터베이스 이름
- `CORE_DB_USERNAME`: 데이터베이스 사용자명
- `CORE_DB_PASSWORD`: 데이터베이스 비밀번호
- `JWT_SECRET`: JWT 시크릿 키

## 새 애플리케이션 추가

새로운 마이크로서비스를 추가할 때:

1. `config/{app-name}/` 디렉토리 생성
2. `{app-name}/application.yml` 파일 생성
3. 필요한 경우 `{app-name}/application-dev.yml`, `{app-name}/application-prod.yml` 파일 생성
4. 애플리케이션의 `application.yml`에서 config import 설정:

```yaml
spring:
  application:
    name: {app-name}
  config:
    import:
      - optional:file:../config/application.yml
      - optional:file:../config/${spring.application.name}/application.yml
      - optional:file:../config/${spring.application.name}/application-${spring.profiles.active}.yml
```

## 주의사항

1. **보안**: 이 디렉토리의 prod 설정 파일에는 민감한 정보를 직접 입력하지 마세요. 환경 변수를 사용하세요.
2. **버전 관리**: prod 설정의 실제 값은 Git에 커밋하지 마세요.
3. **검증**: 새 설정을 추가한 후 반드시 애플리케이션이 정상 시작되는지 확인하세요.