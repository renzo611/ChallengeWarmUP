package com.alkemy.ChallengeWarmUP.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.ChallengeWarmUP.dto.PostFilterDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;

@Component
public class PostSpecifications {
	public Specification<PostEntity> getByFilters(PostFilterDto filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDTO.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            if (filtersDTO.getIdCategoria() != null) {
                Join<PostEntity, CategoriaEntity> join = root.join("categoriasAsociados", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getIdCategoria()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
