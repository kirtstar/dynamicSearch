package com.example.dynamicSearch.impl;

import com.example.dynamicSearch.entities.Order;
import com.example.dynamicSearch.repositories.OrderRepository;
import com.example.dynamicSearch.searchUtilities.CommonSpecification;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements SearchService<Order> {
    private final OrderRepository orderRepository;
    private final CommonSpecification<Order> commonSpec = new CommonSpecification<>();

    @Override
    public Page<Order> findAll(Pageable buildPageable) {
        return orderRepository.findAll(buildPageable);
    }

    @Override
    public Page<Order> findAll(Map<String, Object> params, Pageable page) {
        if (params != null && !params.isEmpty()){
            Specification<Order> spec = commonSpec.buildSpecification(params);
            return orderRepository.findAll(spec, page);
        }
        return orderRepository.findAll(page);
    }
}
