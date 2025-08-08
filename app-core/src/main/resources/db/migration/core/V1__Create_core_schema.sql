-- Flyway Migration V1: Create core schema
-- 작성자: 박성우
-- 작성일: 2025.08.03

-- core 스키마는 이미 application.yml의 datasource URL에서 지정되어 있으므로
-- 테이블만 생성하면 됨

-- 기본 시퀀스 생성 (선택사항)
-- CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;