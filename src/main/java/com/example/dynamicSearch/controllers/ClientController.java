package com.example.dynamicSearch.controllers;

import com.example.dynamicSearch.dtos.ClientDto;
import com.example.dynamicSearch.dtos.PageableRequest;
import com.example.dynamicSearch.dtos.SearchDto;
import com.example.dynamicSearch.entities.Client;
import com.example.dynamicSearch.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController implements SearchApi<Client, ClientDto> {
    private final SearchService<Client> clientSearchService;
    @Override
    @PostMapping("/search")
    public Page<ClientDto> search(SearchDto searchDto) {
        if (searchDto == null){
            return clientSearchService.findAll(PageableRequest.buildPageable(null))
                    .map(this::convertToDto);
        }
        return clientSearchService.findAll(searchDto.getSearch(), PageableRequest.buildPageable(searchDto.getParams()))
                    .map(this::convertToDto);
    }

    @Override
    public ClientDto convertToDto(Client client) {
        return ClientDto.build(client);
    }
}
