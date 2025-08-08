package kr.co.groupware.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 모든 엔티티의 기본 클래스
 * JPA 전환을 고려하여 @MappedSuperclass 어노테이션 위치를 주석으로 표시
 * 
 * @author 박성우
 * @date 2025.08.03
 */
// @MappedSuperclass (JPA 사용 시 활성화)
@Getter
@Setter
public abstract class BaseEntity {
    
    /**
     * 생성일시
     */
    private LocalDateTime createdAt;
    
    /**
     * 생성자
     */
    private String createdBy;
    
    /**
     * 수정일시
     */
    private LocalDateTime updatedAt;
    
    /**
     * 수정자
     */
    private String updatedBy;
    
    /**
     * 삭제일시 (Soft Delete)
     */
    private LocalDateTime deletedAt;
    
    /**
     * 삭제 여부 확인
     * 
     * @return 삭제된 경우 true, 그렇지 않으면 false
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }
    
    /**
     * 생성 정보 설정
     * 
     * @param userId 생성자 ID
     */
    public void setCreatedInfo(String userId) {
        this.createdAt = LocalDateTime.now();
        this.createdBy = userId;
    }
    
    /**
     * 수정 정보 설정
     * 
     * @param userId 수정자 ID
     */
    public void setUpdatedInfo(String userId) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = userId;
    }
    
    /**
     * 삭제 처리 (Soft Delete)
     * 
     * @param userId 삭제자 ID
     */
    public void delete(String userId) {
        this.deletedAt = LocalDateTime.now();
        this.updatedBy = userId;
        this.updatedAt = LocalDateTime.now();
    }
}