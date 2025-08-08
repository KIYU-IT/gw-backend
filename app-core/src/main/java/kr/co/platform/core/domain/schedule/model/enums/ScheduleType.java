package kr.co.platform.core.domain.schedule.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 일정 유형 (예시)
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@RequiredArgsConstructor
public enum ScheduleType {
    
    PERSONAL("개인일정", "개인적인 일정"),
    MEETING("회의", "팀/부서 회의"),
    BUSINESS_TRIP("출장", "업무 출장"),
    VACATION("휴가", "연차/휴가"),
    TRAINING("교육", "교육/훈련"),
    EVENT("행사", "회사 행사");
    
    private final String displayName;
    private final String description;
}