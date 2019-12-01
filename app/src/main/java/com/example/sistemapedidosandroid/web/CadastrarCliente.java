package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.ClienteModel;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarCliente extends AppCompatActivity {

    Button btCadastrar, btCancelar;
    EditText edNome, edSobrenome, edCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        edNome = findViewById(R.id.nome);
        edSobrenome = findViewById(R.id.sobrenome);
        edCpf = findViewById(R.id.cpf);
        btCadastrar = findViewById(R.id.cadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteModel cliente = new ClienteModel();

                cliente.setNome(edNome.getText().toString());
                cliente.setSobrenome(edSobrenome.getText().toString());
                cliente.setCpf(edCpf.getText().toString());

                Call call = new RetrofitInicializador().getClienteService().cadastrar(cliente);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        int resposta = response.code();


                        if (resposta == 500) {
                            Toast.makeText(CadastrarCliente.this, "CPF " + cliente.getCpf() + " já cadastrado", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i("onResponse", "Requisição com sucesso");
                            Toast.makeText(CadastrarCliente.this, "Cliente " + cliente.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(CadastrarCliente.this, ListarCliente.class);
                            startActivity(i);

                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("onFailure", "Requisão falhou");
                    }
                });

            }
        });


    }
}
