package kr.co.platform.core.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Core Service용 Swagger 설정 Common의 SpringDocConfig를 확장
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Configuration
public class SwaggerConfig {

    /**
     * Core Service 전용 OpenAPI 설정 Common의 설정을 오버라이드
     *
     * @return OpenAPI 설정 객체
     */
    @Bean
    @Primary
    public OpenAPI coreServiceOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Core Service API")
                                .description("그룹웨어 핵심 비즈니스 서비스 API")
                                .version("v1.0.0"))
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8080/api")
                                        .description("로컬 개발 서버"),
                                new Server()
                                        .url("https://dev-api.platform.com/api")
                                        .description("개발 서버")));
    }

    /**
     * 일정 관리 API 그룹
     *
     * @return 일정 API 그룹 설정
     */
    @Bean
    public GroupedOpenApi scheduleApi() {
        return GroupedOpenApi.builder()
                .group("schedule")
                .displayName("일정 관리")
                .pathsToMatch("/v1/schedules/**")
                .build();
    }

    /**
     * 사용자 관리 API 그룹
     *
     * @return 사용자 API 그룹 설정
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .displayName("사용자 관리")
                .pathsToMatch("/v1/users/**")
                .build();
    }

    /**
     * 게시판 API 그룹
     *
     * @return 게시판 API 그룹 설정
     */
    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder()
                .group("board")
                .displayName("게시판")
                .pathsToMatch("/v1/boards/**", "/v1/posts/**")
                .build();
    }

    /**
     * 전체 API 그룹
     *
     * @return 전체 API 그룹 설정
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .displayName("전체 API")
                .pathsToMatch("/v1/**")
                .build();
    }
}
