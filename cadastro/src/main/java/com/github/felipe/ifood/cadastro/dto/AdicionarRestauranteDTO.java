package com.github.felipe.ifood.cadastro.dto;

import com.github.felipe.ifood.cadastro.Restaurante;
import com.github.felipe.ifood.cadastro.common.DTO;
import com.github.felipe.ifood.cadastro.common.ValidDTO;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ValidDTO
public class AdicionarRestauranteDTO implements DTO {

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

    @Override
    public boolean isValid(ConstraintValidatorContext constraintViolationContext) {
        constraintViolationContext.disableDefaultConstraintViolation();
        if (Restaurante.find("cnpj", cnpj).count() > 0) {
            constraintViolationContext.buildConstraintViolationWithTemplate("CNPJ Já Cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
