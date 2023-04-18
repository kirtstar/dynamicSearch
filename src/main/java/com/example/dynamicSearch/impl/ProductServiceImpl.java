package com.example.dynamicSearch.impl;

import com.example.dynamicSearch.entities.Client;
import com.example.dynamicSearch.entities.Product;
import com.example.dynamicSearch.repositories.ProductRepository;
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
public class ProductServiceImpl implements SearchService<Product> {
    private final ProductRepository productRepository;
    private final CommonSpecification<Product> commonSpec = new CommonSpecification<>();

    @Override
    public Page<Product> findAll(Pageable buildPageable) {
        return productRepository.findAll(buildPageable);
    }

    @Override
    public Page<Product> findAll(Map<String, Object> params, Pageable page) {
        if (params != null && !params.isEmpty()){
            Specification<Product> spec = commonSpec.buildSpecification(params);
            return productRepository.findAll(spec, page);
        }
        return productRepository.findAll(page);
    }
}
