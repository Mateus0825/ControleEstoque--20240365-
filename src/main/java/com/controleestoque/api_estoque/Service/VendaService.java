package com.controleestoque.api_estoque.Service;

import com.controleestoque.api_estoque.dto.ItemVendaRequest;
import com.controleestoque.api_estoque.dto.VendaRequest;
import com.controleestoque.api_estoque.model.*;
import com.controleestoque.api_estoque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public static class InsufficientStockException extends RuntimeException {
        public InsufficientStockException(String message) {
            super(message);
        }
    }

    @Transactional
    public Venda registrarVenda(VendaRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Venda venda = new Venda();
        venda.setCliente(cliente);

        List<ItemVenda> itens = new ArrayList<>();

        // Validar estoque antes de avançar
        for (ItemVendaRequest item : request.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            Estoque estoque = produto.getEstoque();

            if (estoque.getQuantidade() < item.getQuantidade()) {
                throw new InsufficientStockException(
                        "Estoque insuficiente para: " + produto.getNome()
                );
            }
        }

        // Baixar estoque e montar itens
        for (ItemVendaRequest req : request.getItens()) {

            Produto produto = produtoRepository.findById(req.getProdutoId()).get();
            Estoque estoque = produto.getEstoque();

            estoque.setQuantidade(estoque.getQuantidade() - req.getQuantidade());
            estoqueRepository.save(estoque);

            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(req.getQuantidade());
            item.setPrecoUnitario(BigDecimal.ZERO);
            item.setVenda(venda);

            itens.add(item);
        }

        venda.setItens(itens);

        return vendaRepository.save(venda);
    }
}
