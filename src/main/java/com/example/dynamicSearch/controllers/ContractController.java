package com.example.dynamicSearch.controllers;

import com.example.dynamicSearch.dtos.ContractDto;
import com.example.dynamicSearch.dtos.PageableRequest;
import com.example.dynamicSearch.dtos.SearchDto;
import com.example.dynamicSearch.entities.Contract;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contracts")
public class ContractController implements SearchApi<Contract, ContractDto> {
    private final SearchService<Contract> contractSearchService;
    @Override
    @PostMapping("/search")
    public Page<ContractDto> search(SearchDto searchDto) {
        if (searchDto == null){
            return contractSearchService.findAll(PageableRequest.buildPageable(null))
                    .map(this::convertToDto);
        }
        return contractSearchService.findAll(searchDto.getSearch(), PageableRequest.buildPageable(searchDto.getParams()))
                .map(this::convertToDto);
    }

    @Override
    public ContractDto convertToDto(Contract entity) {
        return ContractDto.build(entity);
    }
}
