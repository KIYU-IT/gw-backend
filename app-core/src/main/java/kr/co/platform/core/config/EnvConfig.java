package kr.co.platform.core.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 환경 변수 설정 클래스
 * .env 파일의 환경 변수를 시스템 프로퍼티로 로드
 *
 * @author 박성우
 * @date 2025.08.08
 */
@Slf4j
@Configuration
public class EnvConfig {

    /**
     * 애플리케이션 시작 시 .env 파일 로드
     * 시스템 프로퍼티로 설정하여 @Value 또는 ${} 표현식으로 사용 가능
     */
    @PostConstruct
    public void loadEnv() {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .directory("../env")  // backend/env 디렉토리 참조
                    .ignoreIfMissing()     // .env 파일이 없어도 오류 발생하지 않음
                    .load();
            
            // .env 파일의 모든 환경 변수를 시스템 프로퍼티로 설정
            dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
            });
            
            log.debug("Environment variables loaded from .env file");
        } catch (Exception e) {
            log.error("Failed to load .env file: {}", e.getMessage());
        }
    }
}