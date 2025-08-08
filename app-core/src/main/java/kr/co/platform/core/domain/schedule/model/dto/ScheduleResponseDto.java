package kr.co.platform.core.domain.schedule.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.platform.core.common.dto.BaseDto;
import kr.co.platform.core.domain.schedule.model.enums.ScheduleType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 일정 응답 DTO (예시)
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDto extends BaseDto {
    
    /**
     * 일정 고유 ID
     */
    private Long id;
    
    /**
     * 사용자 ID
     */
    private String userId;
    
    /**
     * 사용자 이름
     */
    private String userName;
    
    /**
     * 일정 제목
     */
    private String title;
    
    /**
     * 일정 설명
     */
    private String description;
    
    /**
     * 장소
     */
    private String location;
    
    /**
     * 시작 일시
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    
    /**
     * 종료 일시
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    
    /**
     * 종일 일정 여부
     */
    private Boolean allDay;
    
    /**
     * 일정 유형
     */
    private ScheduleType scheduleType;
    
    /**
     * 일정 유형 표시명
     */
    private String scheduleTypeDisplayName;
    
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
    private Boolean isPublic;
    
    /**
     * 수정 가능 여부
     */
    private Boolean editable;
    
    /**
     * 삭제 가능 여부
     */
    private Boolean deletable;
    
    /**
     * 일정 유형 표시명 설정
     * 일정 유형이 설정될 때 자동으로 표시명도 설정
     * 
     * @param scheduleType 일정 유형
     */
    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
        if (scheduleType != null) {
            this.scheduleTypeDisplayName = scheduleType.getDisplayName();
        }
    }
}