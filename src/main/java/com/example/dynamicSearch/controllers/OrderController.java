package com.example.dynamicSearch.controllers;

import com.example.dynamicSearch.dtos.OrderDto;
import com.example.dynamicSearch.dtos.PageableRequest;
import com.example.dynamicSearch.dtos.SearchDto;
import com.example.dynamicSearch.entities.Order;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController implements SearchApi<Order, OrderDto> {
    private final SearchService<Order> orderSearchService;
    @Override
    @PostMapping("/search")
    public Page<OrderDto> search(SearchDto searchDto) {
        if (searchDto == null){
            return orderSearchService.findAll(PageableRequest.buildPageable(null))
                    .map(this::convertToDto);
        }
        return orderSearchService.findAll(searchDto.getSearch(), PageableRequest.buildPageable(searchDto.getParams()))
                .map(this::convertToDto);
    }

    @Override
    public OrderDto convertToDto(Order entity) {
        return OrderDto.build(entity);
    }
}
