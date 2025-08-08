package kr.co.platform.core.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 날짜 관련 유틸리티 클래스
 *
 * @author 박성우
 * @date 2025.08.03
 */
public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMATTER_KO =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");

    private DateUtil() {
        // 유틸리티 클래스는 인스턴스화 방지
    }

    /**
     * LocalDateTime을 문자열로 변환
     *
     * @param dateTime 변환할 날짜시간
     * @return 변환된 문자열 (yyyy-MM-dd HH:mm:ss)
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATETIME_FORMATTER);
    }

    /**
     * LocalDateTime을 한국어 형식 문자열로 변환
     *
     * @param dateTime 변환할 날짜시간
     * @return 변환된 한국어 형식 문자열
     */
    public static String formatDateTimeKo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATETIME_FORMATTER_KO);
    }

    /**
     * LocalDate를 문자열로 변환
     *
     * @param date 변환할 날짜
     * @return 변환된 문자열 (yyyy-MM-dd)
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_FORMATTER);
    }

    /**
     * 문자열을 LocalDateTime으로 변환
     *
     * @param dateTimeStr 변환할 날짜시간 문자열
     * @return 변환된 LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * 문자열을 LocalDate로 변환
     *
     * @param dateStr 변환할 날짜 문자열
     * @return 변환된 LocalDate
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * 두 날짜 사이의 일수 계산
     *
     * @param startDate 시작 날짜
     * @param endDate 종료 날짜
     * @return 두 날짜 사이의 일수
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 오늘 날짜인지 확인
     *
     * @param date 확인할 날짜
     * @return 오늘이면 true, 그렇지 않으면 false
     */
    public static boolean isToday(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.equals(LocalDate.now());
    }

    /**
     * 과거 날짜인지 확인
     *
     * @param dateTime 확인할 날짜시간
     * @return 과거면 true, 그렇지 않으면 false
     */
    public static boolean isPast(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.isBefore(LocalDateTime.now());
    }

    /**
     * 미래 날짜인지 확인
     *
     * @param dateTime 확인할 날짜시간
     * @return 미래면 true, 그렇지 않으면 false
     */
    public static boolean isFuture(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.isAfter(LocalDateTime.now());
    }
}
