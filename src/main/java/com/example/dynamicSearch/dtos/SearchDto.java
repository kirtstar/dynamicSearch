package com.example.dynamicSearch.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private Map<String,Object> search;
    private PageableRequest params;
}
