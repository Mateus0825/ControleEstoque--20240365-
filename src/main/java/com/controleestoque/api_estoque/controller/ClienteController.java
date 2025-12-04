package com.controleestoque.api_estoque.controller;

import com.controleestoque.api_estoque.model.Cliente;
import com.controleestoque.api_estoque.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping
    public List<Cliente> list() {
        return repository.findAll();
    }
}
