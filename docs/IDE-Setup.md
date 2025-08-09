# IDE 설정 가이드

## VSCode 설정

### 필수 Extensions

#### Java 개발
- **Extension Pack for Java** - Microsoft의 Java 개발 통합 팩
  - Language Support for Java(TM) by Red Hat
  - Debugger for Java
  - Java Test Runner
  - Maven for Java
  - Java Dependency Viewer
  - Visual Studio IntelliCode

#### Spring Boot 개발
- **Spring Boot Extension Pack** - Spring Boot 개발 통합 팩
  - Spring Boot Tools
  - Spring Initializr Java Support
  - Spring Boot Dashboard

#### Gradle 빌드
- **Gradle for Java** - Gradle 프로젝트 지원

### 추천 Extensions

#### 코드 품질
- **SonarLint** - 코드 품질 검사
- **Spotless Gradle** - 코드 포맷터 통합

#### Git
- **GitLens** - Git 히스토리 및 blame 보기
- **Git Graph** - Git 브랜치 시각화

#### 유틸리티
- **Docker** - Docker 지원
- **YAML** - YAML 파일 지원
- **XML** - XML 파일 지원 (MyBatis mapper)
- **DotENV** - .env 파일 하이라이팅

### VSCode 설정 파일 (.vscode/settings.json)

```json
{
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.compile.nullAnalysis.mode": "automatic",
  "java.dependency.syncWithFolderExplorer": true,
  "java.format.enabled": false,  // Spotless 사용
  "java.saveActions.organizeImports": true,
  "java.completion.enabled": true,
  "java.completion.importOrder": [
    "java",
    "javax",
    "jakarta",
    "org",
    "com",
    "kr.co.platform"
  ],
  "[java]": {
    "editor.defaultFormatter": "redhat.java",
    "editor.formatOnSave": false  // Spotless 사용
  },
  "spring-boot.ls.java.home": "./bin/jdk-17",
  "java.jdt.ls.java.home": "./bin/jdk-17",
  "files.exclude": {
    "**/.gradle": true,
    "**/build": true,
    "**/.idea": true,
    "**/*.iml": true
  }
}
```

### VSCode 실행 설정 (.vscode/launch.json)

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot - CoreApplication",
      "request": "launch",
      "cwd": "${workspaceFolder}",
      "mainClass": "kr.co.platform.core.CoreApplication",
      "projectName": "app-core",
      "args": "",
      "envFile": "${workspaceFolder}/env/.env"
    },
    {
      "type": "java",
      "name": "Debug Spring Boot",
      "request": "attach",
      "hostName": "localhost",
      "port": 5005
    }
  ]
}
```

## IntelliJ IDEA 설정

### 필수 플러그인
- Spring Boot (Ultimate Edition에 포함)
- Lombok Plugin
- MyBatis Plugin
- Docker Plugin
- .env files support

### 프로젝트 설정

#### 1. JDK 설정
```
File > Project Structure > Project
- Project SDK: 17 (프로젝트 내장 JDK 경로: ./bin/jdk-17)
- Project language level: 17
```

#### 2. Gradle 설정
```
File > Settings > Build, Execution, Deployment > Build Tools > Gradle
- Build and run using: Gradle
- Run tests using: Gradle
- Gradle JVM: Project SDK
```

#### 3. 코드 스타일 설정
```
File > Settings > Editor > Code Style > Java
- Scheme: Google (Spotless와 일치)
- Import Scheme > IntelliJ IDEA code style XML
```

#### 4. Lombok 설정
```
File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors
- Enable annotation processing: ✓
```

### IntelliJ 실행 구성

#### Spring Boot 실행 구성
1. Run > Edit Configurations
2. Add New Configuration > Spring Boot
3. 설정:
   - Name: CoreApplication
   - Main class: kr.co.platform.core.CoreApplication
   - Working directory: $MODULE_WORKING_DIR$
   - Environment variables:
     - SPRING_PROFILES_ACTIVE=dev
   - Use classpath of module: app-core

#### 원격 디버깅 구성
1. Run > Edit Configurations
2. Add New Configuration > Remote JVM Debug
3. 설정:
   - Name: Remote Debug
   - Host: localhost
   - Port: 5005

### IntelliJ 파일 템플릿

#### Java Class 템플릿
```java
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

/**
 * ${NAME}
 *
 * @author ${USER}
 * @date ${DATE}
 */
public class ${NAME} {
}
```

## 공통 설정

### Git Hooks (pre-commit)
```bash
#!/bin/sh
# .git/hooks/pre-commit

echo "Running code format check..."
./gradlew spotlessCheck

if [ $? -ne 0 ]; then
    echo "❌ Code format check failed. Run './gradlew spotlessApply' to fix."
    exit 1
fi

echo "✅ Code format check passed"
```

### 환경변수 설정
두 IDE 모두 다음 환경변수를 설정:
- `JAVA_HOME`: 프로젝트 내장 JDK 경로 (./bin/jdk-17)
- `SPRING_PROFILES_ACTIVE`: 개발 프로필 (dev)

## 문제 해결

### VSCode Java 언어 서버 오류
```bash
# Java Language Server 캐시 삭제
rm -rf ~/.config/Code/User/workspaceStorage/*/redhat.java
```

### IntelliJ 인덱싱 문제
```
File > Invalidate Caches and Restart
```

### Gradle 동기화 문제
```bash
./gradlew clean build --refresh-dependencies
```

## 팀 규칙

1. **코드 포맷팅**: 저장 전 반드시 Spotless 실행
   ```bash
   make format  # 또는 ./gradlew spotlessApply
   ```

2. **커밋 전 검사**:
   ```bash
   make check  # 포맷 + 테스트
   ```
