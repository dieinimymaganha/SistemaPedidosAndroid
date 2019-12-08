package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sistemapedidosandroid.R;

public class Inicio extends AppCompatActivity {

    Button btCliente, btProduto, btPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btCliente = findViewById(R.id.clientes);
        btProduto = findViewById(R.id.produtos);
        btPedido = findViewById(R.id.activity_inicio_pedidos);



        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, ListarCliente.class);
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

        btPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio.this, ListarPedidosPorCliente.class));
            }
        });

    }
}
