# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a platform (그룹웨어) system with MSA architecture:
- **Frontend**: React-based SPA located in `/gwFront` directory
- **Backend**: Spring Boot MSA located in `/backend` directory
  - **common**: Shared library module (non-executable)
  - **app-core**: Main business logic service (전자결재, 사용자, 일정, 게시판)
  - **app.streamhub**: Real-time communication service (WebSocket-based)

The frontend project consists of two templates:
- **Main directory** (`/gwFront`): Development template (start-kit)
- **Full-kit** (`/gwFront/full-kit`): Complete UI component library for reference

## Common Development Commands

### Frontend Development
```bash
# Navigate to frontend directory
cd /mnt/d/gw/gwFront

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

### Testing
No test scripts are currently configured. When implementing tests, update this section.

### Linting and Type Checking
No linting or type checking commands are currently configured. When implementing these tools, update this section.

## Architecture Overview

### Frontend Architecture

#### Technology Stack
- **Build Tool**: Vite
- **UI Framework**: React 18
- **State Management**: Redux Toolkit with RTK Query
- **Routing**: React Router v6
- **Styling**: Tailwind CSS v4
- **UI Components**: Custom components with Headless UI, Radix UI
- **Form Handling**: React Hook Form with Yup validation
- **Charts**: ApexCharts, Chart.js, Recharts
- **Mock Server**: MirageJS for development

#### Key Architectural Patterns

1. **State Management**
   - Redux store configuration in `src/store/index.js`
   - RTK Query for API calls via `src/store/api/apiSlice.js`
   - Feature-based slices in `src/store/api/`

2. **Routing Structure**
   - Main layout wrapper at `src/layout/Layout.jsx`
   - Auth layout at `src/layout/AuthLayout.jsx`
   - Lazy loading for route components

3. **Component Organization**
   - Reusable UI components in `src/components/ui/`
   - Page-specific components in `src/components/partials/`
   - Feature pages in `src/pages/`

4. **Mock API Server**
   - MirageJS server setup in `src/server/index.js`
   - Auth endpoints in `src/server/auth-server.js`
   - Shop endpoints in `src/server/shop-server.js`
   - Calendar endpoints in `src/server/app/calendar/calendar-server.js`

5. **Path Aliasing**
   - `@/` resolves to `src/` directory

### Important Notes

1. **Private Package**: This project contains licensed commercial packages. Keep `"private": true` in package.json and do not upload to public repositories.

2. **Development Approach**: 
   - Use the main directory for actual development
   - Reference full-kit for UI component examples only
   - Copy needed components from full-kit to main project

3. **Node.js Version**: v16+ required, v22.17.1 recommended. See `/gwFront/docs/윈도우_NVM_설치_및_노드_버전_관리.md` for Windows NVM setup.

4. **Mock Data**: The application uses MirageJS for mock API responses during development. Default login credentials are:
   - Email: core@gmail.com
   - Password: core

## Backend Development

### Technology Stack
- **Framework**: Spring Boot 3.3.2
- **Java Version**: 17
- **Build Tool**: Gradle (Groovy DSL)
- **ORM**: MyBatis (planning to migrate to JPA)
- **Database**: MariaDB
- **Web Layer**: Spring Web MVC (No WebFlux)
- **Real-time**: WebSocket (app.streamhub)

### Backend Commands
```bash
# Navigate to backend directory
cd /mnt/d/gw/backend

# Build all modules
./gradlew clean build

# Run specific service
./gradlew :app-core:bootRun
./gradlew :app.streamhub:bootRun

# Run tests
./gradlew test

# Start database with docker-compose
docker-compose -f docker-compose/db/docker-compose.yml up -d
```

### Architecture Principles

1. **MSA Structure**
   - Each service is independently deployable
   - Services communicate via REST APIs
   - Common module shared as dependency

2. **Domain-Driven Design**
   - Package by domain feature (user, schedule, newsfeed, etc.)
   - Layered architecture within each domain

3. **Naming Conventions**
   - NO abbreviations - use full, clear names
   - Examples: `ScheduleServiceImpl`, `UserRequestDto`
   - Entity/DTO separation with clear suffixes

4. **Layer Structure** (per domain)
   - `web/` - Controllers
   - `service/` - Business logic interfaces
   - `service/impl/` - Service implementations
   - `service/factory/` - Service factories
   - `mapper/` - MyBatis mappers
   - `model/` - Entities, DTOs, Enums

5. **Common Module Contents**
   - BaseEntity, BaseDto
   - GlobalExceptionHandler
   - CommonResponse wrapper
   - Generic converters
   - Shared utilities

### Database Access
- MyBatis Mapper Interface + XML approach
- Entity classes for DB mapping
- Separate Request/Response DTOs
- BaseEntity with common fields (createdAt, updatedAt, etc.)

### Future Considerations
- JPA migration path considered in naming/structure
- Kafka/Redis Pub-Sub for event-driven architecture
- API Gateway integration
- Service mesh compatibility

## 코딩 규칙

### 문서 주석 (JavaDoc)
모든 클래스, 인터페이스, 메서드에는 반드시 JavaDoc 주석을 작성해야 합니다.

#### 클래스/인터페이스 주석 형식
```java
/**
 * [클래스/인터페이스 설명]
 *
 * @author 박성우
 * @date 2025.08.03
 */
```

#### 메서드 주석 형식
```java
/**
 * [메서드 설명]
 *
 * @param paramName 파라미터 설명
 * @return 반환값 설명
 * @throws ExceptionType 예외 설명
 */
```

#### 필드 주석 형식
```java
/**
 * [필드 설명]
 */
private String fieldName;
```

### MyBatis Mapper 구조
- Mapper 인터페이스와 XML 파일은 같은 패키지에 위치
- 예: `kr.co.platform.domain.schedule.mapper` 패키지에 `ScheduleMapper.java`와 `ScheduleMapper.xml`이 함께 존재
- mapper-locations 설정: `classpath:kr/co/platform/**/*.xml`