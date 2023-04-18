package com.example.dynamicSearch.searchUtilities;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public class CommonSpecification<T> {
    public Specification<T> buildSpecification(Map<String, Object> params){
        Specification<T> spec = Specification.where(null);
        for (Map.Entry<String, Object> entry : params.entrySet()){
            if (entry.getValue() != null && !"".equals(entry.getValue())) {
                if (entry.getValue().toString().matches("^\\[.*\\]$")){
                    spec = spec.and(buildOrSpecification(entry.getKey(), entry.getValue()));
                } else spec = buildAndSpecification(entry.getKey(), entry.getValue(), spec);
            }
        }
        return spec;
    }

    private Specification<T> buildAndSpecification(String key, Object value, Specification<T> spec){
        if (key.contains(".")) {
            List<String> partsOfPathToSearch = new ArrayList<>(Arrays.asList(key.split("\\.")));

            spec = spec.and(this.hasValue(partsOfPathToSearch, value));
        } else {
            spec = spec.and(this.hasValue(List.of(key), value));
        }
        return spec;
    }

    private Specification<T> buildOrSpecification(String key, Object values){
        Specification<T> spec = Specification.where(null);

        for (Object value : (ArrayList<Object>) values) {
            if (key.contains(".")) {
                List<String> partsOfPathToSearch = new ArrayList<>(Arrays.asList(key.split("\\.")));
                spec = spec.or(this.hasValue(partsOfPathToSearch, value));
            } else {
                spec = spec.or(this.hasValue(List.of(key), value));
            }
        }
        return spec;
    }

    public Specification<T> hasValue(List<String> partsOfPathToSearch, Object value) {
        return (root, query, builder) -> {
            query.distinct(true);
            if (root.get(partsOfPathToSearch.get(0)).getJavaType() == Set.class){
                Join<Object, Object> join = root.join(partsOfPathToSearch.get(0));
                return buildPredicateByPath(partsOfPathToSearch, value, join, builder);
            } else {
                Path path = root.get(partsOfPathToSearch.get(0));
                return buildPredicateByPath(partsOfPathToSearch, value, path, builder);
            }
        };
    }

    private Predicate buildPredicateByPath(List<String> partsOfPathToSearch, Object searchParam, Path path, CriteriaBuilder builder) {
        for (int indexOfPathPart = 1; indexOfPathPart < partsOfPathToSearch.size(); indexOfPathPart++) {
            path = path.get(partsOfPathToSearch.get(indexOfPathPart));
        }
        if (path.getJavaType() == String.class) {
            return builder.like(builder.lower(path), "%" + searchParam.toString().toLowerCase() + "%");
        } else if (path.getJavaType().isEnum()) {
            return builder.equal(path.as(String.class), searchParam);
        } else if (path.getJavaType() == Long.class) {
            return builder.like(path.as(String.class), searchParam.toString() + "%");
        } else {
            return builder.equal(path, searchParam);
        }
    }
}
