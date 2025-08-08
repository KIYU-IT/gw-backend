package kr.co.platform.core.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 모든 DTO의 기본 클래스
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Setter
public abstract class BaseDto implements Serializable {

    /** 생성일시 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /** 생성자 */
    private String createdBy;

    /** 수정일시 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    /** 수정자 */
    private String updatedBy;
}
