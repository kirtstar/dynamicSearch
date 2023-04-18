package com.example.dynamicSearch.services;

import com.example.dynamicSearch.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface SearchService<T> {
    Page<T> findAll(Pageable buildPageable);
    Page<T> findAll(Map<String, Object> params, Pageable page);

}
