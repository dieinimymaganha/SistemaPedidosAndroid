package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

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
}
