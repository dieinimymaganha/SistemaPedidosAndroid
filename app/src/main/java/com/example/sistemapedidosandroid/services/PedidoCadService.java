package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.PedidoCad;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoCadService {


    @POST("pedidos/")
    Call<Void> cadastrar(@Body PedidoCad pedidoCad);


}
