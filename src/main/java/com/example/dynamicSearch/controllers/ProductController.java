package com.example.dynamicSearch.controllers;

import com.example.dynamicSearch.dtos.PageableRequest;
import com.example.dynamicSearch.dtos.ProductDto;
import com.example.dynamicSearch.dtos.SearchDto;
import com.example.dynamicSearch.entities.Product;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController implements SearchApi<Product, ProductDto> {
    private final SearchService<Product> productSearchService;

    @Override
    @PostMapping("/search")
    public Page<ProductDto> search(SearchDto searchDto) {
        if (searchDto == null) {
            return productSearchService.findAll(PageableRequest.buildPageable(null))
                    .map(this::convertToDto);
        }
        return productSearchService.findAll(searchDto.getSearch(), PageableRequest.buildPageable(searchDto.getParams()))
                .map(this::convertToDto);
    }

    @Override
    public ProductDto convertToDto(Product entity) {
        return ProductDto.build(entity);
    }
}
