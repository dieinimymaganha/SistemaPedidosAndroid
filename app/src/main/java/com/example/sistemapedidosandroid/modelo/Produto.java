package com.example.sistemapedidosandroid.modelo;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Produto implements Serializable {
    @JsonProperty("id")
    private Long id;
    private String descricao;


    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {
        return descricao;
    }
}
