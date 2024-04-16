package com.lihang.etvms.infrastructure.repository.role;

import com.lhhj.hft.common.entity.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 角色搜索条件
 *
 * @date 2023/1/3
 **/
public class RoleEntitySpecification extends SearchSpecification implements Specification<RoleEntity> {

    private static final String ROLE_NAME_FIELD = "name";

    private String roleNameContent;

    public RoleEntitySpecification(String roleNameContent) {
        this.roleNameContent = roleNameContent;
    }

    @Override
    public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (null == roleNameContent) {
            roleNameContent = "";
        }
        Predicate usernamePredicate = criteriaBuilder.like(root.get(ROLE_NAME_FIELD).as(String.class), this.like(roleNameContent));
        return criteriaBuilder.and(usernamePredicate);
    }
}
