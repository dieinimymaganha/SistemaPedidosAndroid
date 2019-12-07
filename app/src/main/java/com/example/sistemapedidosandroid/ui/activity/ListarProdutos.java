package com.example.sistemapedidosandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;
import static com.example.sistemapedidosandroid.ui.activity.ConstantesActivities.DESCRICAO_PRODUTO;
import static com.example.sistemapedidosandroid.ui.activity.ConstantesActivities.ID_PRODUTO;

public class ListarProdutos extends AppCompatActivity {

    public static final String PRODUTOS = "Produtos";
    SearchView searchView;
    ListView lista_produtos;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        setTitle(PRODUTOS);
        inicializacaoDosCampos();
        configuraFabNovoPrduto();
    }

    private void configuraFabNovoPrduto() {

        FloatingActionButton botaoNovoProduto = findViewById(R.id.activity_lista_produtos_fab_novo_produto);
        botaoNovoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroProduto();
            }
        });
    }

    private void abreCadastroProduto() {
        startActivity(new Intent(this, CadastrarProduto.class));
    }

    private void inicializacaoDosCampos() {
        lista_produtos = findViewById(R.id.activity_lista_produtos);
    }


    @Override
    protected void onResume() {
        super.onResume();

        carregaDadosProdutos();
    }


    private void carregaDadosProdutos() {

        Call<List<Produto>> call = new RetrofitInicializador().getProdutoService().lista();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                criaAdapterListView(response);
                criarSearchView();
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

            }
        });

    }

    private void criarSearchView() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    private void criaAdapterListView(Response<List<Produto>> response) {
        List<Produto> produtos = response.body();
        arrayAdapter = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, produtos);
        lista_produtos.setAdapter(arrayAdapter);
        searchView = findViewById(R.id.activity_lista_produtos_searchView);
        registerForContextMenu(lista_produtos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.activity_lista_produtos_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_menu_lista_produto_remover) {
            excluirProduto(item);
        }

        if (itemId == R.id.activity_menu_lista_produto_alterar) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Produto produtoEscolhido = (Produto) arrayAdapter.getItem(menuInfo.position);
            Log.i("cliente selecionado ", " id" + produtoEscolhido.getId());
            Intent editarProduto = new Intent(getApplicationContext(), EditarProduto.class);
            editarProduto.putExtra(ID_PRODUTO, produtoEscolhido.getId());
            editarProduto.putExtra(DESCRICAO_PRODUTO, produtoEscolhido.getDescricao());
            startActivity(editarProduto);
        }

        return super.onContextItemSelected(item);
    }

    private void excluirProduto(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Produto produtoEscolhido = (Produto) arrayAdapter.getItem(menuInfo.position);
        Long p = produtoEscolhido.getId();

        Call<Void> call = new RetrofitInicializador().getProdutoService().deletar(p);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                int resposta = response.code();
                if (resposta == 500) {
                    Toast.makeText(ListarProdutos.this, "Falha ao Excluir", Toast.LENGTH_SHORT).show();

                } else {
                    carregaDadosProdutos();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


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
