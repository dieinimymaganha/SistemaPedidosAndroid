package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sistemapedidosandroid.R;

public class Inicio extends AppCompatActivity {

    Button btCliente, btProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btCliente = findViewById(R.id.clientes);
        btProduto = findViewById(R.id.produtos);



        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, Cliente.class);
                startActivity(i);
            }
        });

        btProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, ListarProdutos.class);
                startActivity(i);
            }
        });

    }
}
