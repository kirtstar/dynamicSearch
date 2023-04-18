package com.example.dynamicSearch.dtos;

import com.example.dynamicSearch.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;


    private String name;


    private String phone;

    public static ClientDto build(Client client){
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .build();
    }
}
