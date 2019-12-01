package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.ClienteModel;
import com.example.sistemapedidosandroid.web.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClienteService {

    @POST("clientes/")
    Call<Void> cadastrar(@Body ClienteModel cliente);


    @GET("clientes/")
    Call <List<ClienteModel>> lista();

    @GET("clientes/{id}")
    Call <ClienteModel> find_cliente(@Path("id") Long id);

    @DELETE("clientes/{id}")
    default Call<Void> deletar(@Path("id") Long id) {
        return null;
    }
}
