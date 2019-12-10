package backend.backend.controller.form;

import java.util.ArrayList;
import java.util.List;

import backend.backend.model.Cliente;
import backend.backend.model.ItemPedido;
import backend.backend.model.Pedido;
import backend.backend.model.Produto;
import backend.backend.repository.ClienteRepository;
import backend.backend.repository.ProdutoRepository;

public class PedidoForm {

	private Long id;
	private String cpf;
	private String descricaoProduto;
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Pedido converter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
		List<ItemPedido> listaitens = new ArrayList<ItemPedido>();

		Produto produto = produtoRepository.findByDescricao(descricaoProduto);
		ItemPedido itens = new ItemPedido();

		itens.setProduto(produto);
		itens.setQuantidade(quantidade);

		listaitens.add(itens);

		Cliente cliente = clienteRepository.findByCpf(cpf);
		return new Pedido(cliente, listaitens);
	}
}
