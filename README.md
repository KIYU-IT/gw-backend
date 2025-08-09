# Platform Project

## 빠른 시작

```bash
# 도움말 보기
make help

# 개발 시작 (포맷팅 → 빌드 → 실행)
make dev

# 코드 포맷팅
make format

# 프로젝트 빌드
make build

# 애플리케이션 실행
make run
```

## 주요 Make 명령어

### 코드 품질
- `make lint` - 코드 검사만 실행 (수정 없음)
- `make format` - 코드 자동 포맷팅 (Python의 ruff format 같은 느낌)
- `make check` - 전체 코드 품질 검사

### 빌드
- `make build` - 프로젝트 빌드 (테스트 포함)
- `make build-skip-test` - 빌드 (테스트 제외)
- `make clean` - 빌드 파일 삭제
- `make rebuild` - 클린 후 재빌드

### 실행
- `make run` - 개발 모드로 실행
- `make run-prod` - 운영 모드로 실행
- `make stop` - 실행 중인 프로세스 종료

### 데이터베이스
- `make db-up` - MariaDB 시작
- `make db-down` - MariaDB 중지
- `make db-logs` - 로그 확인
- `make db-reset` - DB 초기화

### 복합 명령어
- `make dev` - 개발 워크플로우 (포맷팅→빌드→실행)
- `make ci` - CI 파이프라인
- `make fresh` - 완전 새로고침

## 프로젝트 구조

```
backend/
├── app-core/           # 핵심 비즈니스 서비스
├── config/             # 외부 설정 파일
├── docker-compose/     # Docker 설정
├── env/               # 환경 변수 (.env)
├── gradle/            # Gradle 설정
│   ├── spotless.gradle    # 코드 포맷터
│   └── error-prone.gradle # 버그 검출
└── Makefile           # 명령어 통합
```

## 기술 스택

- **Java 17** + **Spring Boot 3.3.2**
- **Gradle** (빌드 도구)
- **MyBatis** (ORM)
- **MariaDB** (데이터베이스)
- **Spotless** (코드 포맷터)
- **Error Prone** (정적 분석)

## 개발 환경 설정

1. Java 17 설치
2. Docker 설치 (DB용)
3. 환경 변수 설정
   ```bash
   cp env/.env.example env/.env
   # .env 파일 편집
   ```

4. 데이터베이스 시작
   ```bash
   make db-up
   ```

5. 개발 시작
   ```bash
   make dev
   ```

## 코드 스타일

이 프로젝트는 **Google Java Format**을 사용합니다.
코드를 커밋하기 전에 항상 포맷팅을 실행하세요:

```bash
make format
```

## 문서

- [개발환경 설정 가이드](docs/개발환경_설정_가이드.md)
- [IDE별 익스텐션 세팅 가이드](docs/IDE-Setup.md)
- [백엔드 아키텍처 가이드](docs/백엔드_아키텍처_가이드.md)
- [프로젝트 구조 가이드](docs/프로젝트_구조_가이드.md)
- [코드 품질 유지 가이드](docs/코드_품질_유지_가이드.md)
