package com.github.felipe.ifood.cadastro.common;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstraintViolationImpl {

    @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero")
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro", required = true)
    private final String mensagem;

    private ConstraintViolationImpl(ConstraintViolation<?> violation) {
        this.atributo = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2).collect(Collectors.joining("."));
        this.mensagem = violation.getMessage();
    }

    public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
        return new ConstraintViolationImpl(violation);
    }

    public String getAtributo() {
        return atributo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
