package kr.co.groupware.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Core 애플리케이션 진입점
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@SpringBootApplication
public class CoreApplication {
    
    /**
     * 애플리케이션 메인 메소드
     * 
     * @param args 명령행 인수
     */
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}