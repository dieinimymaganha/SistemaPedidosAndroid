package com.example.sistemapedidosandroid.ui.activity;

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
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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
        btCancelar = findViewById(R.id.cancelar);


        // Criando mascara para cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);
        // Fim mascara


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edNome.getText().toString();
                String sobrenome = edSobrenome.getText().toString();
                String cpf = edCpf.getText().toString();


                ClienteModel cliente = new ClienteModel();
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCpf(cpf);

                if (nome == null || nome.equals("")) {
                    edNome.setError("Este campo é obrigatório");
                }

                if (sobrenome == null || sobrenome.equals("")) {

                    edSobrenome.setError(("Este campo é obrigatório"));
                }
                if (cpf == null || cpf.equals("")) {

                    edCpf.setError("Este Campo é obrigatório");
                } else {
                    Call call = new RetrofitInicializador().getClienteService().cadastrar(cliente);

                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            int resposta = response.code();


                            if (resposta == 500) {
                                edCpf.setError("CPF já cadastrado");
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


            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastrarCliente.this, Cliente.class);
                startActivity(i);
            }
        });


    }
}
