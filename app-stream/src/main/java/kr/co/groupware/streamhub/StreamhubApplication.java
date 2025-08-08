package kr.co.groupware.streamhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Streamhub 애플리케이션 진입점
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@SpringBootApplication(scanBasePackages = {"kr.co.groupware.streamhub", "kr.co.groupware.common"})
public class StreamhubApplication {
    
    /**
     * 애플리케이션 메인 메소드
     * 
     * @param args 명령행 인수
     */
    public static void main(String[] args) {
        SpringApplication.run(StreamhubApplication.class, args);
    }
}