package backend.backend.controller.notfound;

public class PedidoNotFoundException extends RuntimeException {

	public PedidoNotFoundException(Long id) {
		super("Could not find pedido " + id);
	}

}
