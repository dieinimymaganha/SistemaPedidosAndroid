package com.example.sistemapedidosandroid.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Produto implements Serializable{
    private Integer id;
    private String descricao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
