package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.ClienteModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteService {

    @POST("clientes/")
    Call<Void> cadastrar(@Body ClienteModel cliente);
}
