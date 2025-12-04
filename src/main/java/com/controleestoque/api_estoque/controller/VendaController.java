package com.controleestoque.api_estoque.controller;

import com.controleestoque.api_estoque.dto.VendaRequest;
import com.controleestoque.api_estoque.model.Venda;
import com.controleestoque.api_estoque.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<?> registrarVenda(@RequestBody VendaRequest request) {
        try {
            Venda venda = vendaService.registrarVenda(request);
            return ResponseEntity.ok(venda);
        } catch (VendaService.InsufficientStockException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }
}
