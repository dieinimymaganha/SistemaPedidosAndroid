package com.example.sistemapedidosandroid.modelo;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Cliente implements Serializable {

    @JsonProperty("id")
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;


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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " " + sobrenome + " CPF: " + cpf;
    }
}
