package backend.backend.controller.notfound;

public class ProdutoNotFoundException extends RuntimeException {

	public ProdutoNotFoundException(Long id) {
		super("Could not find Produto" + id);
	}

}
