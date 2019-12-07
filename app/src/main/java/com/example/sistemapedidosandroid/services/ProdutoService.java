package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProdutoService {

    @POST("produtos/")
    Call<Void> cadastrar(@Body Produto produto);

    @GET("produtos/")
    Call <List<Produto>> lista();

}


