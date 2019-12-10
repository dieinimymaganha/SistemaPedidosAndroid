package com.example.sistemapedidosandroid.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.ItemPedido;
import com.example.sistemapedidosandroid.modelo.Pedido;

import java.util.List;

public class ListaPedidosPorClienteAdapter extends BaseAdapter {
    private final List<Pedido> pedidos;
    private final Context context;

    public ListaPedidosPorClienteAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
    }


    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Pedido getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return pedidos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pedido pedido = pedidos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.itens_pedidos_por_cliente, parent, false);
        }
        criaDadosAdapter(pedido, view);
        return view;
    }

    private void criaDadosAdapter(Pedido pedido, View view) {
        List<ItemPedido> itens = pedido.getItempedido();
        TextView produto = view.findViewById(R.id.itens_pedido_cliente_produto);
        TextView quantidade = view.findViewById(R.id.itens_pedido_cliente_quantidade);
        for (ItemPedido item : itens) {
            produto.setText("Produto: " + item.getProduto().toString());
            quantidade.setText("Quantidade: " + item.getQuantidade());
        }
        TextView numeroPedido = view.findViewById(R.id.itens_pedido_cliente_id);
        numeroPedido.setText("Numero do pedido: " + pedido.getId());
    }

}
