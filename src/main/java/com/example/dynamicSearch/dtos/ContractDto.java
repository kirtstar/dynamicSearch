package com.example.dynamicSearch.dtos;

import com.example.dynamicSearch.entities.Contract;
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
public class ContractDto {

    private Long id;
    private String name;
    private String phone;
    private ClientDto client;
    private List<OrderDto> orders;

    public static ContractDto build(Contract contract){
        return ContractDto.builder()
                .id(contract.getId())
                .name(contract.getName())
                .phone(contract.getPhone())
                .client(ClientDto.build(contract.getClient()))
                .orders(contract.getOrders().stream().map(OrderDto::buildForIdAndNumber).collect(Collectors.toList()))
                .build();
    }

}
