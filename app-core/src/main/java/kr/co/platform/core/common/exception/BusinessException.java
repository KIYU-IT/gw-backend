package kr.co.platform.core.common.exception;

import lombok.Getter;

/**
 * 비즈니스 로직 예외 기본 클래스
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
public class BusinessException extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final Object[] args;
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.args = null;
    }
    
    public BusinessException(ErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
        this.args = args;
    }
    
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.args = null;
    }
    
    public BusinessException(ErrorCode errorCode, Throwable cause, Object... args) {
        super(String.format(errorCode.getMessage(), args), cause);
        this.errorCode = errorCode;
        this.args = args;
    }
}