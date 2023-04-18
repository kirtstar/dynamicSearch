package com.example.dynamicSearch.impl;

import com.example.dynamicSearch.entities.Client;
import com.example.dynamicSearch.repositories.ClientRepository;
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
public class ClientServiceImpl implements SearchService<Client> {

    private final ClientRepository clientRepository;
    private final CommonSpecification<Client> commonSpec = new CommonSpecification<>();
    @Override
    public Page<Client> findAll(Pageable buildPageable) {
        return clientRepository.findAll(buildPageable);
    }

    @Override
    public Page<Client> findAll(Map<String, Object> params, Pageable page) {
        if (params != null && !params.isEmpty()){
            Specification<Client> spec = commonSpec.buildSpecification(params);
            return clientRepository.findAll(spec, page);
        }
        return clientRepository.findAll(page);
    }
}
