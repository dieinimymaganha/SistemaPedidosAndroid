package backend.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import backend.backend.dto.ClienteDto;
import backend.backend.form.ClienteForm;
import backend.backend.model.Cliente;
import backend.backend.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	List<Cliente> all() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	Cliente one(@PathVariable Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}

	@PostMapping
	Cliente newCliente(@RequestBody Cliente newCliente) {
		return clienteRepository.save(newCliente);
	}

	@PutMapping("/{id}")
	Cliente replaceCliente(@RequestBody Cliente newCliente, @PathVariable Long id) {
		return clienteRepository.findById(id).map(cliente -> {
			cliente.setNome(newCliente.getNome());
			cliente.setSobrenome(newCliente.getSobrenome());
			cliente.setCpf(newCliente.getCpf());
			return clienteRepository.save(cliente);
		}).orElseGet(() -> {
			newCliente.setId(id);
			return clienteRepository.save(newCliente);
		});

	}

}
