package kr.co.platform.core.domain.schedule.web;

import kr.co.platform.core.common.dto.CommonResponse;
import kr.co.platform.core.domain.schedule.model.dto.ScheduleResponseDto;
import kr.co.platform.core.domain.schedule.model.enums.ScheduleType;
import kr.co.platform.core.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 일정 관리 컨트롤러
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Validated
@Tag(name = "일정 관리", description = "일정 생성, 수정, 삭제 및 조회 API")
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    /**
     * 오늘의 일정 조회
     * 
     * @param userId 사용자 ID
     * @return 오늘의 일정 목록
     */
    @Operation(summary = "오늘의 일정 조회", description = "현재 사용자의 오늘 일정 목록을 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/today")
    public ResponseEntity<CommonResponse<List<ScheduleResponseDto>>> getTodaySchedules(
            @Parameter(description = "사용자 ID", required = false) 
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        
        log.debug("Get today's schedules: userId={}", userId);
        
        // Mock 데이터 생성
        List<ScheduleResponseDto> mockSchedules = new ArrayList<>();
        
        mockSchedules.add(ScheduleResponseDto.builder()
                .id(1L)
                .title("월간 회의")
                .description("월간 실적 검토 및 계획 수립")
                .startDate(LocalDateTime.now().withHour(10).withMinute(0))
                .endDate(LocalDateTime.now().withHour(12).withMinute(0))
                .location("대회의실")
                .scheduleType(ScheduleType.MEETING)
                .color("#FF5733")
                .allDay(false)
                .isPublic(true)
                .editable(true)
                .deletable(true)
                .build());
                
        mockSchedules.add(ScheduleResponseDto.builder()
                .id(2L)
                .title("프로젝트 리뷰")
                .description("신규 프로젝트 진행 상황 점검")
                .startDate(LocalDateTime.now().withHour(14).withMinute(0))
                .endDate(LocalDateTime.now().withHour(15).withMinute(30))
                .location("소회의실 A")
                .scheduleType(ScheduleType.MEETING)
                .color("#33FF57")
                .allDay(false)
                .isPublic(true)
                .editable(true)
                .deletable(true)
                .build());
        
        return ResponseEntity.ok(CommonResponse.success(mockSchedules));
    }
    
}