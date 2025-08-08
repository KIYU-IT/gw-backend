package kr.co.groupware.core.domain.schedule.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.groupware.core.domain.schedule.service.ScheduleService;

/**
 * 일정 서비스 구현체
 * 
 * @author 박성우
 * @date 2025.08.03
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService {
    
}