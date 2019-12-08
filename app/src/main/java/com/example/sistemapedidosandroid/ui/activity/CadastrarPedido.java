package com.example.sistemapedidosandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

public class CadastrarPedido extends AppCompatActivity {

    TextView txtNome, txtCpf;
    Long id;
    Spinner spinner_produtos;

    Long id_produto;

    Button btCadastrar;

    ArrayAdapter arrayAdapterProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pedido);
        spinner_produtos = findViewById(R.id.spinner_pedido);
        inicializacaoDosCampos();

        Intent i = getIntent();
        id = i.getLongExtra("id", 0);
        String nome = i.getStringExtra("nome").toString();
        String sobrenome = i.getStringExtra("sobrenome").toString();
        String cpf = i.getStringExtra("cpf").toString();
        txtCpf.setText(cpf);
        txtNome.setText(nome + " " + sobrenome);

        carregaProdutos();


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CadastrarPedido.this, " Escolha " + spinner_produtos.getSelectedItem(), Toast.LENGTH_SHORT).show();

//                AdapterView.OnItemSelectedListener escolha = new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                        Long ssss = spinner_produtos.getSelectedItemId();
//
//                        Toast.makeText(getBaseContext(), "o item " + ssss, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                };
//
//                spinner_produtos.setOnItemSelectedListener(escolha);


            }
        });


    }

    private void carregaProdutos() {
        Call<List<Produto>> call = new RetrofitInicializador().getProdutoService().lista();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {

                List<Produto> produtos = response.body();
                arrayAdapterProduto = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, produtos);
                spinner_produtos.setAdapter(arrayAdapterProduto);

            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

            }
        });
    }

    private void inicializacaoDosCampos() {
        btCadastrar = findViewById(R.id.acttivity_cadastrar_pedido_cadastrar);
        txtCpf = findViewById(R.id.activity_cadastrar_pedido_cpf);
        txtNome = findViewById(R.id.activity_cadastrar_pedido_nome);
    }


}
