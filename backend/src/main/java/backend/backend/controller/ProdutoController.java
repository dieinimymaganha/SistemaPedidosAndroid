package backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.controller.notfound.ProdutoNotFoundException;
import backend.backend.model.Cliente;
import backend.backend.model.Produto;
import backend.backend.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	List<Produto> all() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{id}")
	Produto one(@PathVariable Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
	}

	@PostMapping
	Produto newProduto(@RequestBody Produto newProduto) {
		return produtoRepository.save(newProduto);
	}

	@PutMapping("/{id}")
	Produto replaceProduto(@RequestBody Produto newProduto, @PathVariable Long id) {
		return produtoRepository.findById(id).map(produto -> {
			produto.setDescricao(newProduto.getDescricao());
			return produtoRepository.save(produto);
		}).orElseGet(() -> {
			newProduto.setId(id);
			return produtoRepository.save(newProduto);
		});
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}

}
