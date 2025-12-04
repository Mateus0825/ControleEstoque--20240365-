package com.controleestoque.api_estoque.dto;

import lombok.Data;
import java.util.List;

@Data
public class VendaRequest {
    private Long clienteId;
    private List<ItemVendaRequest> itens;
}

