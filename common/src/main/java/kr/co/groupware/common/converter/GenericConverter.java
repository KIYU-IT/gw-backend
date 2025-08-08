package kr.co.groupware.common.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 엔티티와 DTO 간 변환을 위한 제네릭 컨버터 인터페이스
 * 
 * @param <E> Entity 타입
 * @param <D> DTO 타입
 * @author 박성우
 * @date 2025.08.03
 */
public interface GenericConverter<E, D> {
    
    /**
     * Entity를 DTO로 변환
     * 
     * @param entity 변환할 엔티티
     * @return 변환된 DTO
     */
    D toDto(E entity);
    
    /**
     * DTO를 Entity로 변환
     * 
     * @param dto 변환할 DTO
     * @return 변환된 엔티티
     */
    E toEntity(D dto);
    
    /**
     * Entity 리스트를 DTO 리스트로 변환
     * 
     * @param entityList 변환할 엔티티 리스트
     * @return 변환된 DTO 리스트
     */
    default List<D> toDtoList(List<E> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * DTO 리스트를 Entity 리스트로 변환
     * 
     * @param dtoList 변환할 DTO 리스트
     * @return 변환된 엔티티 리스트
     */
    default List<E> toEntityList(List<D> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}