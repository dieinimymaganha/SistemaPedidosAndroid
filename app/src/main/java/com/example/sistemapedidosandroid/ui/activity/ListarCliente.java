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
import com.example.sistemapedidosandroid.modelo.Cliente;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        configuraFabNovoCliente();

    }

    public void configuraFabNovoCliente() {
        FloatingActionButton botaoNovoCliente = findViewById(R.id.activity_lista_clientes_fab_novo_cliente);
        botaoNovoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroCliente();
            }
        });
    }

    private void abreCadastroCliente() {
        startActivity(new Intent(this, CadastrarCliente.class));
    }

    private void inicializacaoDosCampos() {
        lista_cliente = findViewById(R.id.activity_lista_cliente);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaDadosClientes();
    }

    private void carregaDadosClientes() {
        Call<List<Cliente>> call = new RetrofitInicializador().getClienteService().lista();

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                criaAdapterListView(response);
                criaSearchView();
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
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

    private void criaAdapterListView(Response<List<Cliente>> response) {
        List<Cliente> clientes = response.body();

        arrayAdapter = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, clientes);

        lista_cliente.setAdapter(arrayAdapter);

        searchView = findViewById(R.id.activity_lista_clientes_searchView);

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
        Cliente clienteEscolhido = (Cliente) arrayAdapter.getItem(menuInfo.position);
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
        Cliente clienteEscolhido = (Cliente) arrayAdapter.getItem(menuInfo.position);
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
                    carregaDadosClientes();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure", "Requisão falhou");
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
