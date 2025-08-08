package kr.co.groupware.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 공통 API 응답 래퍼 클래스
 * 
 * @param <T> 응답 데이터 타입
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    
    /**
     * 성공 여부
     */
    private final boolean success;
    
    /**
     * 응답 데이터
     */
    private final T data;
    
    /**
     * 에러 정보
     */
    private final ErrorResponse error;
    
    /**
     * 응답 시간
     */
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    
    /**
     * 성공 응답 생성
     * 
     * @param <T> 응답 데이터 타입
     * @param data 응답 데이터
     * @return 성공 응답 객체
     */
    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }
    
    /**
     * 성공 응답 생성 (데이터 없음)
     * 
     * @param <T> 응답 데이터 타입
     * @return 성공 응답 객체
     */
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .success(true)
                .build();
    }
    
    /**
     * 실패 응답 생성
     * 
     * @param <T> 응답 데이터 타입
     * @param code 에러 코드
     * @param message 에러 메시지
     * @return 실패 응답 객체
     */
    public static <T> CommonResponse<T> error(String code, String message) {
        return CommonResponse.<T>builder()
                .success(false)
                .error(ErrorResponse.of(code, message))
                .build();
    }
    
    /**
     * 실패 응답 생성 (상세 정보 포함)
     * 
     * @param <T> 응답 데이터 타입
     * @param code 에러 코드
     * @param message 에러 메시지
     * @param detail 상세 정보
     * @return 실패 응답 객체
     */
    public static <T> CommonResponse<T> error(String code, String message, Object detail) {
        return CommonResponse.<T>builder()
                .success(false)
                .error(ErrorResponse.of(code, message, detail))
                .build();
    }
    
    /**
     * 에러 응답 정보
     * 
     * @author 박성우
     * @date 2025.08.03
     */
    @Getter
    @Builder
    public static class ErrorResponse {
        private final String code;
        private final String message;
        
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final Object detail;
        
        public static ErrorResponse of(String code, String message) {
            return ErrorResponse.builder()
                    .code(code)
                    .message(message)
                    .build();
        }
        
        public static ErrorResponse of(String code, String message, Object detail) {
            return ErrorResponse.builder()
                    .code(code)
                    .message(message)
                    .detail(detail)
                    .build();
        }
    }
}