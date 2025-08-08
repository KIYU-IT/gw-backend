package kr.co.platform.core.domain.schedule.model.entity;

import java.time.LocalDateTime;

import kr.co.platform.core.common.entity.BaseEntity;
import kr.co.platform.core.domain.schedule.model.enums.ScheduleType;
import lombok.*;

/**
 * 일정 엔티티 (예시)
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class ScheduleEntity extends BaseEntity {

    /** 일정 고유 ID */
    private Long id;

    /** 사용자 ID */
    private String userId;

    /** 일정 제목 */
    private String title;

    /** 일정 설명 */
    private String description;

    /** 장소 */
    private String location;

    /** 시작 일시 */
    private LocalDateTime startDate;

    /** 종료 일시 */
    private LocalDateTime endDate;

    /** 종일 일정 여부 */
    private Boolean allDay;

    /** 일정 유형 */
    private ScheduleType scheduleType;

    /** 알림 시간 (분 단위) */
    private Integer reminderMinutes;

    /** 표시 색상 (HEX) */
    private String color;

    /** 공개 여부 */
    private Boolean isPublic;

    /** 기본값 설정 일정 생성 시 필수 기본값들을 설정한다 */
    public void setDefaults() {
        if (this.allDay == null) {
            this.allDay = false;
        }
        if (this.scheduleType == null) {
            this.scheduleType = ScheduleType.PERSONAL;
        }
        if (this.isPublic == null) {
            this.isPublic = true;
        }
        if (this.color == null) {
            this.color = "#4285F4"; // 기본 파란색
        }
    }
}
