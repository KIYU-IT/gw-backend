package kr.co.platform.core.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

/**
 * SpringDoc OpenAPI 3.0 (Swagger) 설정
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Configuration
public class SpringDocConfig {

    @Value("${spring.application.name:platform}")
    private String applicationName;

    /**
     * OpenAPI 3.0 설정 빈 생성
     *
     * @return OpenAPI 설정 객체
     */
    @Bean
    public OpenAPI openAPI() {
        // 보안 스키마 정의
        SecurityScheme securityScheme =
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("X-User-Id")
                        .description("사용자 ID (임시 인증 방식)");

        // 보안 요구사항
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("userId");

        // 서버 정보
        Server localServer = new Server().url("http://localhost:8080").description("로컬 개발 서버");

        Server devServer = new Server().url("https://dev-api.platform.com").description("개발 서버");

        return new OpenAPI()
                .info(apiInfo())
                .servers(List.of(localServer, devServer))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("userId", securityScheme));
    }

    /**
     * API 정보 설정
     *
     * @return API 메타데이터 정보
     */
    private Info apiInfo() {
        return new Info()
                .title(applicationName + " API Documentation")
                .description("그룹웨어 시스템 REST API 문서")
                .version("v1.0.0")
                .contact(
                        new Contact()
                                .name("platform Development Team")
                                .email("dev@platform.com")
                                .url("https://platform.com"))
                .license(new License().name("Private License").url("https://platform.com/license"));
    }
}
