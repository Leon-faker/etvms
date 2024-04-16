package com.lihang.etvms.infrastructure.repository.user;

import com.lhhj.hft.common.entity.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 用户搜索条件
 *
 * @date 2023/1/3
 **/
public class UserEntitySpecification extends SearchSpecification implements Specification<UserEntity> {

    /**
     * 需要与对应Entity中所要查询的字段保持一致
     */
    private static final String USERNAME_FIELD = "username";

    private String usernameContent;

    public UserEntitySpecification(String usernameContent) {
        this.usernameContent = usernameContent;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (null == usernameContent) {
            this.usernameContent = "";
        }
        Predicate usernamePredicate = criteriaBuilder.like(root.get(USERNAME_FIELD).as(String.class), this.like(this.usernameContent));
        return criteriaBuilder.and(usernamePredicate);
    }
}
