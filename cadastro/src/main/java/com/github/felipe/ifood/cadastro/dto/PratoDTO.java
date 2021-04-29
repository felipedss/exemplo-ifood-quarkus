package com.github.felipe.ifood.cadastro.dto;

import com.github.felipe.ifood.cadastro.Restaurante;

import java.math.BigDecimal;

public class PratoDTO {

    private Long id;

    private String nome;

    private BigDecimal preco;

    private Restaurante restaurante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
