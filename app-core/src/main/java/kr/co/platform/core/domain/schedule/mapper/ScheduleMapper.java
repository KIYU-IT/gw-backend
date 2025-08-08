package kr.co.platform.core.domain.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.platform.core.domain.schedule.model.entity.ScheduleEntity;

/**
 * 일정 MyBatis Mapper
 *
 * @author 박성우
 * @date 2025.08.03
 */
@Mapper
public interface ScheduleMapper {

    /**
     * 일정 저장
     *
     * @param schedule 일정 엔티티
     * @return 영향받은 행 수
     */
    int insertSchedule(ScheduleEntity schedule);
}
