package com.example.dynamicSearch.impl;

import com.example.dynamicSearch.entities.Contract;
import com.example.dynamicSearch.repositories.ContractRepository;
import com.example.dynamicSearch.searchUtilities.CommonSpecification;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ContractServiceImpl implements SearchService<Contract> {
    private final ContractRepository contractRepository;
    private final CommonSpecification<Contract> commonSpec = new CommonSpecification<>();
    @Override
    public Page<Contract> findAll(Pageable buildPageable) {
        return contractRepository.findAll(buildPageable);
    }

    @Override
    public Page<Contract> findAll(Map<String, Object> params, Pageable page) {
        if (params != null && !params.isEmpty()){
            Specification<Contract> spec = commonSpec.buildSpecification(params);
            return contractRepository.findAll(spec, page);
        }
        return contractRepository.findAll(page);
    }
}
