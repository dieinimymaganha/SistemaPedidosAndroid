package com.example.sistemapedidosandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.PedidoCad;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

public class CadastrarPedidoCliente extends AppCompatActivity {

    public static final String CADASTRA_PEDIDO_CLIENTE = "Cadastra Pedido Cliente";
    TextView txtNome, txtCpf, txtQuantidade;
    Spinner spinner_produtos;
    Button btCadastrar, btCancelar;
    ArrayAdapter arrayAdapterProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle(CADASTRA_PEDIDO_CLIENTE);


        inicializacaoDosCampos();

        Intent i = getIntent();
        String nome = i.getStringExtra("nome").toString();
        String sobrenome = i.getStringExtra("sobrenome").toString();
        String cpf = i.getStringExtra("cpf").toString();

        txtCpf.setText(cpf);
        txtNome.setText(nome + " " + sobrenome);

        carregaProdutos();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String qtd = txtQuantidade.getText().toString();

                if (qtd == null || qtd.equals("")) {
                    txtQuantidade.setError("Este campo é obrigatório");
                } else {
                    int quantidade = Integer.parseInt(qtd);


                    PedidoCad pedidoCad = new PedidoCad();
                    pedidoCad.setCpf(cpf);
                    pedidoCad.setDescricaoProduto(spinner_produtos.getSelectedItem().toString());
                    pedidoCad.setQuantidade(quantidade);
                    Call<Void> call = new RetrofitInicializador().getPedidoCadService().cadastrar(pedidoCad);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.i(">>>> ", "cadastrou");
                            Toast.makeText(CadastrarPedidoCliente.this, "Pedido cadastrado", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        setContentView(R.layout.activity_cadastrar_pedido_cliente);
        spinner_produtos = findViewById(R.id.spinner_pedido);
        btCadastrar = findViewById(R.id.acttivity_cadastrar_pedido_cadastrar);
        btCancelar = findViewById(R.id.activity_cadastrar_pedido_cancelar);
        txtCpf = findViewById(R.id.activity_cadastrar_pedido_cpf);
        txtNome = findViewById(R.id.activity_cadastrar_pedido_nome);
        txtQuantidade = findViewById(R.id.activity_cadastrar_pedido_quantidade);

    }

    //Cria o menu para enviar para o home
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_home_home) {
            startActivity(new Intent(this, Inicio.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // fim do menu home


}
