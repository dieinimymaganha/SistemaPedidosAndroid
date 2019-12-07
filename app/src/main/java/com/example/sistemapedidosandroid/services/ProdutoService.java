package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProdutoService {

    @POST("produtos/")
    Call<Void> cadastrar(@Body Produto produto);

    @GET("produtos/")
    Call<List<Produto>> lista();

    @DELETE("produtos/{id}")
    default Call<Void> deletar(@Path("id") Long id) {
        return null;
    }

    @PUT("produtos/{id}")
    Call<Void> alterar(@Path("id") Long id, @Body Produto produto);


}


