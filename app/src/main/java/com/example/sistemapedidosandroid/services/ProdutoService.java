package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.ProdutoModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProdutoService {

    @POST("produtos/")
    Call<Void> cadastrar(@Body ProdutoModel produto);
}

