package kr.co.groupware.streamhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정
 * 실제 운영에서는 JWT 토큰 기반 인증 추가 필요
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * 보안 필터 체인 설정
     * WebSocket 엔드포인트는 인증 없이 접근 허용
     * 
     * @param http HTTP 보안 설정
     * @return 보안 필터 체인
     * @throws Exception 설정 오류 시
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/ws/**").permitAll()  // WebSocket 엔드포인트는 일단 모두 허용
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}