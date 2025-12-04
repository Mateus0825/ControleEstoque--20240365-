package com.controleestoque.api_estoque.dto;

import lombok.Data;

@Data
public class ItemVendaRequest {
    private Long produtoId;
    private Integer quantidade;
}
