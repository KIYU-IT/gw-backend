package kr.co.groupware.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 문자열 관련 유틸리티 클래스
 * 
 * @author 박성우
 * @date 2025.08.03
 */
public class StringUtil {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^(01[016789])-?(\\d{3,4})-?(\\d{4})$"
    );
    
    private StringUtil() {
        // 유틸리티 클래스는 인스턴스화 방지
    }
    
    /**
     * 문자열이 null이거나 비어있는지 확인
     * 
     * @param str 확인할 문자열
     * @return 비어있으면 true, 그렇지 않으면 false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * 문자열이 null이 아니고 비어있지 않은지 확인
     * 
     * @param str 확인할 문자열
     * @return 비어있지 않으면 true, 그렇지 않으면 false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * null을 빈 문자열로 변환
     * 
     * @param str 변환할 문자열
     * @return null이면 빈 문자열, 그렇지 않으면 원본 문자열
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
    
    /**
     * 빈 문자열을 null로 변환
     * 
     * @param str 변환할 문자열
     * @return 빈 문자열이면 null, 그렇지 않으면 원본 문자열
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }
    
    /**
     * 이메일 형식 검증
     * 
     * @param email 검증할 이메일 주소
     * @return 올바른 이메일 형식이면 true, 그렇지 않으면 false
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * 전화번호 형식 검증
     * 
     * @param phoneNumber 검증할 전화번호
     * @return 올바른 전화번호 형식이면 true, 그렇지 않으면 false
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (isEmpty(phoneNumber)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
    
    /**
     * 전화번호 포맷팅
     * 
     * @param phoneNumber 포맷팅할 전화번호
     * @return 포맷팅된 전화번호 (xxx-xxxx-xxxx)
     */
    public static String formatPhoneNumber(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            return phoneNumber;
        }
        
        String digits = phoneNumber.replaceAll("-", "");
        if (digits.length() == 10) {
            return String.format("%s-%s-%s", 
                digits.substring(0, 3), 
                digits.substring(3, 6), 
                digits.substring(6)
            );
        } else if (digits.length() == 11) {
            return String.format("%s-%s-%s", 
                digits.substring(0, 3), 
                digits.substring(3, 7), 
                digits.substring(7)
            );
        }
        
        return phoneNumber;
    }
    
    /**
     * UUID 생성
     * 
     * @return 생성된 UUID 문자열
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * UUID 생성 (하이픈 제거)
     * 
     * @return 하이픈이 제거된 UUID 문자열
     */
    public static String generateUuidWithoutHyphen() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 문자엱 마스킹 처리
     * 
     * @param str 마스킹할 문자열
     * @param visibleStart 앞에서 보여줄 글자 수
     * @param visibleEnd 뒤에서 보여줄 글자 수
     * @return 마스킹된 문자열
     */
    public static String mask(String str, int visibleStart, int visibleEnd) {
        if (isEmpty(str) || str.length() <= visibleStart + visibleEnd) {
            return str;
        }
        
        StringBuilder masked = new StringBuilder();
        masked.append(str.substring(0, visibleStart));
        
        int maskLength = str.length() - visibleStart - visibleEnd;
        masked.append("*".repeat(maskLength));
        
        if (visibleEnd > 0) {
            masked.append(str.substring(str.length() - visibleEnd));
        }
        
        return masked.toString();
    }
    
    /**
     * 이름 마스킹 (첫글자와 마지막 글자만 표시)
     * 
     * @param name 마스킹할 이름
     * @return 마스킹된 이름
     */
    public static String maskName(String name) {
        if (isEmpty(name)) {
            return name;
        }
        
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        } else if (name.length() >= 3) {
            return name.charAt(0) + "*".repeat(name.length() - 2) + name.charAt(name.length() - 1);
        }
        
        return name;
    }
}