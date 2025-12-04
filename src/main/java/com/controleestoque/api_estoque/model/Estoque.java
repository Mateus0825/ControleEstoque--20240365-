package com.controleestoque.api_estoque.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_estoque")
@Data
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private String local;
}