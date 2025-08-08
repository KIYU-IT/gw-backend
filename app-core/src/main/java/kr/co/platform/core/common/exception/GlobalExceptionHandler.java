package kr.co.platform.core.common.exception;

import kr.co.platform.core.common.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 전역 예외 처리 핸들러
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 비즈니스 예외 처리
     * 
     * @param e 비즈니스 예외
     * @return 에러 응답
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException e) {
        log.error("BusinessException: {}", e.getMessage(), e);
        ErrorCode errorCode = e.getErrorCode();
        
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(CommonResponse.error(errorCode.getCode(), e.getMessage()));
    }
    
    /**
     * @Valid 검증 실패 예외 처리
     * 
     * @param e 메소드 인수 검증 예외
     * @return 에러 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.error(
                    ErrorCode.INVALID_INPUT_VALUE.getCode(),
                    ErrorCode.INVALID_INPUT_VALUE.getMessage(),
                    errors
                ));
    }
    
    /**
     * @Validated 검증 실패 예외 처리
     * 
     * @param e 바인딩 예외
     * @return 에러 응답
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<CommonResponse<Void>> handleBindException(BindException e) {
        log.error("BindException: {}", e.getMessage(), e);
        
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.error(
                    ErrorCode.INVALID_INPUT_VALUE.getCode(),
                    ErrorCode.INVALID_INPUT_VALUE.getMessage(),
                    errors
                ));
    }
    
    /**
     * 타입 불일치 예외 처리
     * 
     * @param e 메소드 인수 타입 불일치 예외
     * @return 에러 응답
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<CommonResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException: {}", e.getMessage(), e);
        
        String message = String.format("'%s' 값이 올바르지 않습니다.", e.getName());
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.error(
                    ErrorCode.INVALID_TYPE_VALUE.getCode(),
                    message
                ));
    }
    
    /**
     * 지원하지 않는 HTTP Method 예외 처리
     * 
     * @param e HTTP 메소드 비지원 예외
     * @return 에러 응답
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<CommonResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage(), e);
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CommonResponse.error(
                    ErrorCode.METHOD_NOT_ALLOWED.getCode(),
                    ErrorCode.METHOD_NOT_ALLOWED.getMessage()
                ));
    }
    
    /**
     * Constraint 위반 예외 처리
     * 
     * @param e 제약조건 위반 예외
     * @return 에러 응답
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<CommonResponse<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage(), e);
        
        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.error(
                    ErrorCode.INVALID_INPUT_VALUE.getCode(),
                    message
                ));
    }
    
    /**
     * 그 외 예외 처리
     * 
     * @param e 일반 예외
     * @return 에러 응답
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.error(
                    ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                    ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
                ));
    }
}