package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sistemapedidosandroid.R;

public class Produto extends AppCompatActivity {

    Button btCadastrar, btPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        btCadastrar = findViewById(R.id.cadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Produto.this, CadastrarProduto.class);
                startActivity(i);
            }
        });
    }
}
