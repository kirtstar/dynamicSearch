package com.example.dynamicSearch.dtos;

import com.example.dynamicSearch.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private Long number;
    private ContractDto contract;
    private List<ProductDto> products;

    public static OrderDto build(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .number(order.getNumber())
                .contract(ContractDto.build(order.getContract()))
                .products(order.getProducts().stream().map(ProductDto::build).collect(Collectors.toList()))
                .build();
    }

    public static OrderDto buildForIdAndNumber(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .number(order.getNumber())
                .products(order.getProducts().stream().map(ProductDto::build).collect(Collectors.toList()))
                .build();
    }

}
