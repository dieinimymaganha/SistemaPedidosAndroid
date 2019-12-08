package com.example.sistemapedidosandroid.modelo;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {

    private Long id;
    private Cliente cliente;
    private List<ItemPedido> itempedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItempedido() {
        return itempedido;
    }

    public void setItempedido(List<ItemPedido> itempedido) {
        this.itempedido = itempedido;
    }

    @NonNull
    @Override
    public String toString() {
        return cliente.getNome() + " " +  getItempedido();
    }
}
