package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.ProdutoModel;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarProduto extends AppCompatActivity {
    Button btCadastrar, btCancelar;
    EditText edDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        edDescricao = findViewById(R.id.descricao);
        btCadastrar = findViewById(R.id.cadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdutoModel produto = new ProdutoModel();

                produto.setDescricao(edDescricao.getText().toString());

                Call call = new RetrofitInicializador().getProdutoService().cadastrar(produto);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.i("onResponse", "Requisição com sucesso");
                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("onFailure","Requisão falhou");
                    }
                });

            }
        });
    }
}
