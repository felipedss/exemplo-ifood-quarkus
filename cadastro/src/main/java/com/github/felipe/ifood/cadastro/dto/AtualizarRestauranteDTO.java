package com.github.felipe.ifood.cadastro.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AtualizarRestauranteDTO {

    @NotEmpty
    @Size(min = 3, max = 40)
    private String nome;

    @NotEmpty
    @Size(min = 3, max = 40)
    private String proprietario;

    @CNPJ
    private String cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
