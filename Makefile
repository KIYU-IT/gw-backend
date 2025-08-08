# Backend Makefile - 자주 사용하는 명령어 모음
# Usage: make [command]

.PHONY: help
help: ## 도움말 표시
	@echo "사용 가능한 명령어:"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2}'
	@echo ""

# ========== 코드 품질 ==========
.PHONY: lint
lint: ## 코드 검사만 실행 (수정 없음)
	./gradlew spotlessCheck --parallel

.PHONY: format
format: ## 코드 자동 포맷팅
	./gradlew spotlessApply --parallel

.PHONY: check
check: ## 전체 코드 품질 검사 (lint + 컴파일 + 테스트)
	./gradlew check --parallel

# ========== 빌드 ==========
.PHONY: build
build: ## 프로젝트 빌드 (테스트 포함)
	./gradlew build --parallel

.PHONY: build-skip-test
build-skip-test: ## 프로젝트 빌드 (테스트 제외)
	./gradlew build -x test --parallel

.PHONY: clean
clean: ## 빌드 파일 삭제
	./gradlew clean

.PHONY: rebuild
rebuild: ## 클린 후 재빌드 (캐시 정리 포함)
	@echo "Gradle 캐시 정리 중..."
	@./gradlew --stop || true
	@rm -rf .gradle/configuration-cache || true
	./gradlew clean
	./gradlew build --parallel

# ========== 실행 ==========
.PHONY: run
run: ## app-core 실행 (개발 모드)
	./gradlew :app-core:bootRun

.PHONY: run-prod
run-prod: ## app-core 실행 (운영 모드)
	SPRING_PROFILES_ACTIVE=prod ./gradlew :app-core:bootRun

.PHONY: stop
stop: ## 실행 중인 모든 Java 프로세스 종료
	@pkill -f "gradle.*bootRun" || true
	@pkill -f "java.*app-core" || true
	@echo "프로세스 종료 완료"

# ========== 테스트 ==========
.PHONY: test
test: ## 테스트 실행
	./gradlew test --parallel

.PHONY: test-report
test-report: ## 테스트 실행 후 리포트 열기
	./gradlew test --parallel
	@echo "테스트 리포트: file://$(PWD)/app-core/build/reports/tests/test/index.html"

# ========== 데이터베이스 ==========
.PHONY: db-up
db-up: ## Docker로 MariaDB 시작
	docker-compose -f docker-compose/infra/docker-compose.db.yml up -d

.PHONY: db-down
db-down: ## Docker MariaDB 중지
	docker-compose -f docker-compose/infra/docker-compose.db.yml down

.PHONY: db-logs
db-logs: ## MariaDB 로그 확인
	docker-compose -f docker-compose/infra/docker-compose.db.yml logs -f

.PHONY: db-reset
db-reset: ## MariaDB 초기화 (데이터 삭제)
	docker-compose -f docker-compose/infra/docker-compose.db.yml down -v
	docker-compose -f docker-compose/infra/docker-compose.db.yml up -d

# ========== 의존성 ==========
.PHONY: deps
deps: ## 의존성 업데이트
	./gradlew dependencies --refresh-dependencies

.PHONY: deps-tree
deps-tree: ## 의존성 트리 출력
	./gradlew :app-core:dependencies

# ========== 복합 명령어 ==========
.PHONY: dev
dev: format build run ## 포맷팅 → 빌드 → 실행 (개발 워크플로우)

.PHONY: ci
ci: format check build ## CI 파이프라인 (포맷팅 → 검사 → 빌드)

.PHONY: fresh
fresh: clean deps format build ## 완전 새로고침 (클린 → 의존성 → 포맷팅 → 빌드)

# ========== 정보 ==========
.PHONY: info
info: ## 프로젝트 정보 출력
	@echo "프로젝트: Backend MSA"
	@echo "Java 버전:"
	@java -version 2>&1 | head -n 1
	@echo "Gradle 버전:"
	@./gradlew --version | grep "Gradle" | head -n 1
	@echo "활성 프로필: $${SPRING_PROFILES_ACTIVE:-default}"

.PHONY: tasks
tasks: ## Gradle 태스크 목록
	./gradlew tasks

# 기본 명령어
.DEFAULT_GOAL := help