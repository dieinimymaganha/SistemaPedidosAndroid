package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sistemapedidosandroid.R;

public class Cliente extends AppCompatActivity {

    Button btCadastrar, btPesquisar, btvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btCadastrar = findViewById(R.id.cadastrar);
        btPesquisar = findViewById(R.id.pesquisar);
        btvoltar = findViewById(R.id.voltar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cliente.this, CadastrarCliente.class);
                startActivity(i);
            }
        });

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cliente.this,ListarCliente.class);
                startActivity(i);
            }
        });

        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cliente.this, Inicio.class);
                startActivity(i);
            }
        });


    }
}
