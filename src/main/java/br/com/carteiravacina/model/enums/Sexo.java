package br.com.carteiravacina.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    MASCULINO("M"),
    FEMININO("F"),
    OUTRO("O");

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static Sexo fromCodigo(String codigo) {
        for (Sexo s : values()) {
            if (s.codigo.equalsIgnoreCase(codigo)) return s;
        }
        throw new IllegalArgumentException("Sexo inválido: " + codigo + ". Use M, F ou O.");
    }
}
