package com.example.sistemapedidosandroid.web;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.ClienteModel;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

public class ListarCliente extends AppCompatActivity {


    public static final String TITULO_APPBAR = "Lista de Clientes";
    SearchView searchView;

    ListView lista_cliente;


    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        lista_cliente = findViewById(R.id.lista_cliente);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaDadosClientes();
    }

    private void carregaDadosClientes() {
        Call<List<ClienteModel>> call = new RetrofitInicializador().getClienteService().lista();

        call.enqueue(new Callback<List<ClienteModel>>() {
            @Override
            public void onResponse(Call<List<ClienteModel>> call, Response<List<ClienteModel>> response) {
                criaAdapterListView(response);
                criaSearchView();
            }

            @Override
            public void onFailure(Call<List<ClienteModel>> call, Throwable t) {
                Log.e("OnResponse", t.getMessage());
            }
        });
    }

    private void criaSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void criaAdapterListView(Response<List<ClienteModel>> response) {
        List<ClienteModel> clientes = response.body();

        arrayAdapter = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, clientes);

        lista_cliente.setAdapter(arrayAdapter);

        searchView = findViewById(R.id.searchView);

        registerForContextMenu(lista_cliente);
        Log.i("OnResponse", response.message());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_cliente_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();


        if (itemId == R.id.excluir) {
            excluirCliente(item);
        }

        if (itemId == R.id.alterar) {
            alterarCliente(item);
        }
        return super.onContextItemSelected(item);
    }

    private void alterarCliente(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ClienteModel clienteEscolhido = (ClienteModel) arrayAdapter.getItem(menuInfo.position);
        Log.i("onResponse", "Requisição com sucesso " + clienteEscolhido.getId());
        Intent i = new Intent(getApplicationContext(), Editar_cliente.class);
        i.putExtra("id", clienteEscolhido.getId());
        i.putExtra("nome", clienteEscolhido.getNome());
        i.putExtra("sobrenome", clienteEscolhido.getSobrenome());
        i.putExtra("cpf", clienteEscolhido.getCpf());
        startActivity(i);
    }

    private void excluirCliente(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ClienteModel clienteEscolhido = (ClienteModel) arrayAdapter.getItem(menuInfo.position);
        Long c = clienteEscolhido.getId();
        Call<Void> call = new RetrofitInicializador().getClienteService().deletar(c);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int resposta = response.code();
                Log.i("onResponse", "Requisição com sucesso " + clienteEscolhido.getId());

                if (resposta == 500) {
                    Toast.makeText(ListarCliente.this, "Cliente Possui pedido em aberto"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure", "Requisão falhou");
            }
        });
    }
}
