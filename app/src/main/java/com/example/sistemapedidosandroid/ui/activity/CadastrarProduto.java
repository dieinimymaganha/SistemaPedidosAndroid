package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarProduto extends AppCompatActivity {
    public static final String CADASTRAR_PRODUTO = "Cadastrar Produto";
    Button btCadastrar, btCancelar;
    EditText edDescricao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        setTitle(CADASTRAR_PRODUTO);
        inicializacaoDosCampos();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraProduto();

            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cadastraProduto() {
        Produto produto = new Produto();
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

    private void inicializacaoDosCampos() {
        edDescricao = findViewById(R.id.descricao);
        btCadastrar = findViewById(R.id.cadastrar);
        btCancelar = findViewById(R.id.cancelar);
    }
}
