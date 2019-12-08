package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PedidoService {

    @GET("pedidos/clientes/1")
    Call<List<Pedido>> lista();
}
