package kr.co.groupware.core.domain.schedule.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.co.groupware.core.domain.schedule.model.enums.ScheduleType;

import java.time.LocalDateTime;

/**
 * 일정 생성/수정 요청 DTO (예시)
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequestDto {
    
    /**
     * 일정 제목
     */
    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 200, message = "일정 제목은 200자를 초과할 수 없습니다.")
    private String title;
    
    /**
     * 일정 설명
     */
    @Size(max = 1000, message = "일정 설명은 1000자를 초과할 수 없습니다.")
    private String description;
    
    /**
     * 장소
     */
    @Size(max = 200, message = "장소는 200자를 초과할 수 없습니다.")
    private String location;
    
    /**
     * 시작 일시
     */
    @NotNull(message = "시작 일시는 필수입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    
    /**
     * 종료 일시
     */
    @NotNull(message = "종료 일시는 필수입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    
    /**
     * 종일 일정 여부
     */
    @Builder.Default
    private Boolean allDay = false;
    
    /**
     * 일정 유형
     */
    @NotNull(message = "일정 유형은 필수입니다.")
    private ScheduleType scheduleType;
    
    /**
     * 알림 시간 (분 단위)
     */
    private Integer reminderMinutes;
    
    /**
     * 표시 색상 (HEX)
     */
    private String color;
    
    /**
     * 공개 여부
     */
    @Builder.Default
    private Boolean isPublic = true;
    
    /**
     * 참석자 사용자 ID 목록
     */
    private String[] participantUserIds;
    
    /**
     * 유효성 검증
     * 일정 데이터의 비즈니스 규칙을 검증한다
     * 
     * @throws IllegalArgumentException 유효성 검증 실패 시
     */
    public void validate() {
        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("종료 일시는 시작 일시보다 이후여야 합니다.");
            }
        }
        
        if (reminderMinutes != null && reminderMinutes < 0) {
            throw new IllegalArgumentException("알림 시간은 0 이상이어야 합니다.");
        }
        
        if (color != null && !color.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("색상은 HEX 형식이어야 합니다. (예: #FF0000)");
        }
    }
}