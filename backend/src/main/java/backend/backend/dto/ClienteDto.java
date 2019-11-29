package backend.backend.dto;

import java.util.List;
import java.util.stream.Collectors;
import backend.backend.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sobrenome;
	private String cpf;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.cpf = cliente.getCpf();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto :: new).collect(Collectors.toList());
	}

}
