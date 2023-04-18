package com.example.dynamicSearch.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dynamicSearch.dtos.SearchDto;

/**
 * Интерфейс для поиска/фильтрации в контроллерах.
 *
 * @param <T> класс сущности.
 * @param <U> класс DTO для сущности.
 */
public interface SearchApi<T, U> {
    @PostMapping
    Page<U> search(@RequestBody(required = false) SearchDto searchDto);

    U convertToDto(T entity);
}
