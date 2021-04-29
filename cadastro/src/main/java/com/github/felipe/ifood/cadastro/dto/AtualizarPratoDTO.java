package com.github.felipe.ifood.cadastro.dto;

import java.math.BigDecimal;

public class AtualizarPratoDTO {

    private String nome;

    private BigDecimal preco;

    private RestauranteDTO restaurante;

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

    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }

}
