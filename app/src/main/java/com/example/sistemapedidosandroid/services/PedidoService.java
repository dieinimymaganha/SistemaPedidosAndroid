package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PedidoService {

    @GET("pedidos/")
    Call<List<Pedido>> lista();


    @GET("pedidos/clientes/{id}")
    Call<List<Pedido>> listarPedidosCliente(@Path("id") Long id);

}
