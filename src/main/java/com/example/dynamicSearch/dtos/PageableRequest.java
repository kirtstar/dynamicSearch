package com.example.dynamicSearch.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Data
public class PageableRequest {
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private static final Integer DEFAULT_PAGE = 0;
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;
    private static final String DEFAULT_SORT_PROPERTY = "id";

    //"Номер страницы"
    Integer page = DEFAULT_PAGE;

    //"Кол-во элементов на странице (по умолчанию 20)"
    Integer size = DEFAULT_PAGE_SIZE;

    //Направление сортировки (по умолчанию DESC)", allowableValues = "ASC, DESC"
    Sort.Direction sortDirection = DEFAULT_SORT_DIRECTION;

    //value = "Поле для сортировки (по умолчанию id)"
    String sortProperty;

    public void setSize(Integer size) {
        this.size = size > 0 ? size : DEFAULT_PAGE_SIZE;
    }

    @JsonIgnore
    public static Pageable buildPageable(PageableRequest request) {
        if (request == null) {
            Sort sort = Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_PROPERTY);
            return PageRequest.of(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, sort);
        } else {
            String sortProperty = StringUtils.hasText(
                    request.getSortProperty()) ? request.getSortProperty() : DEFAULT_SORT_PROPERTY;
            Sort sort = Sort.by(request.getSortDirection(), sortProperty);
            return PageRequest.of(request.getPage(), request.getSize(), sort);
        }
    }
}
