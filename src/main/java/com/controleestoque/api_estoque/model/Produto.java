package com.controleestoque.api_estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "tb_produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // N:1 Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // 1:1 Estoque
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    // N:M Fornecedores
    @ManyToMany
    @JoinTable(
        name = "produto_fornecedor",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private List<Fornecedor> fornecedores;
}