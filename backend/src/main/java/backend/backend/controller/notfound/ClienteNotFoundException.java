package backend.backend.controller.notfound;

public class ClienteNotFoundException extends RuntimeException {

	public ClienteNotFoundException(Long id) {
		super("Could not find cliente " + id);
	}

}
