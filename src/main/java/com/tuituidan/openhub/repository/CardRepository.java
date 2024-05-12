package com.tuituidan.openhub.repository;

import com.tuituidan.openhub.bean.entity.Card;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * CardRepository.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2020/10/2
 */
public interface CardRepository extends JpaRepository<Card, String> {

    /**
     * findByAuditTrue.
     *
     * @return List
     */
    List<Card> findByAuditTrue();

    /**
     * findByAuditFalseOrderByApplyTimeDesc
     *
     * @param pageRequest pageRequest
     * @return List
     */
    Page<Card> findByAuditFalse(PageRequest pageRequest);

    /**
     * countByAuditFalse
     *
     * @return Long
     */
    Long countByAuditFalse();

    /**
     * findByTag.
     *
     * @param category category
     * @return List
     */
    List<Card> findByAuditTrueAndCategory(String category);

    /**
     * findByCategoryIn.
     *
     * @param categories categories
     * @return List
     */
    List<Card> findByAuditTrueAndCategoryIn(List<String> categories);

    /**
     * findByTitleLikeOrContentLike.
     *
     * @param keyword keyword
     * @return List
     */
    @Query("select u from Card u "
            + "where u.audit = true and lower(u.title) like %?1% or lower(u.content) like %?1%")
    List<Card> findByKeywords(String keyword);

    /**
     * getMaxSort
     *
     * @param category category
     * @return Integer
     */
    @Query("select coalesce(max(sort),0) from Card where audit = true and category = ?1")
    Integer getMaxSort(String category);

    /**
     * deleteByCategory
     *
     * @param categoryIds categoryIds
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByCategoryIn(Collection<String> categoryIds);

}
