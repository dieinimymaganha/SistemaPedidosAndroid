package backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.controller.notfound.ClienteNotFoundException;
import backend.backend.controller.notfound.PedidoNotFoundException;
import backend.backend.model.Pedido;
import backend.backend.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	List<Pedido> all() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/{id}")
	Pedido one(@PathVariable Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));
	}

	@PostMapping
	Pedido newPedido(@RequestBody Pedido newPedido) {
		return pedidoRepository.save(newPedido);
	}

}
