package backend.backend.dto;

import java.util.List;

import backend.backend.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sobrenome;
	private String cpg;

	public ClienteDto(Long id, String nome, String sobrenome, String cpg) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpg = cpg;
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

	public String getCpg() {
		return cpg;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto:: new).collect(Collectors.toList());
	}

}
