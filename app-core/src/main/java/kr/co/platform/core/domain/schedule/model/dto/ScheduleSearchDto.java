package kr.co.platform.core.domain.schedule.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.co.platform.core.domain.schedule.model.enums.ScheduleType;
import lombok.*;

/**
 * 일정 검색 조건 DTO (예시)
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleSearchDto {

    /** 사용자 ID */
    private String userId;

    /** 검색 시작 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /** 검색 종료 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /** 일정 유형 */
    private ScheduleType scheduleType;

    /** 검색 키워드 (제목, 설명, 장소) */
    private String keyword;

    /** 공개 일정만 조회 */
    @Builder.Default private Boolean publicOnly = false;

    /** 페이지 번호 (1부터 시작) */
    @Builder.Default private Integer page = 1;

    /** 페이지 크기 */
    @Builder.Default private Integer pageSize = 20;

    /** 정렬 필드 */
    @Builder.Default private String sortBy = "startDate";

    /** 정렬 방향 (ASC, DESC) */
    @Builder.Default private String sortDirection = "ASC";

    /**
     * Offset 계산 페이지네이션을 위한 오프셋 값을 계산
     *
     * @return 오프셋 값
     */
    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    /** 기본값 설정 검색 조건의 기본값들을 설정하고 유효성을 검증 */
    public void setDefaults() {
        if (this.page == null || this.page < 1) {
            this.page = 1;
        }
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = 20;
        }
        if (this.pageSize > 100) {
            this.pageSize = 100; // 최대 100개로 제한
        }
    }
}
