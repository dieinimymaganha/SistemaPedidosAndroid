package backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.dto.ClienteDto;
import backend.backend.model.Cliente;
import backend.backend.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping
	public List<ClienteDto> lista(){
		List<Cliente> clientes = clienteRepository.findAll();
		
		return ClienteDto.converter(clientes);
	}
	


}
