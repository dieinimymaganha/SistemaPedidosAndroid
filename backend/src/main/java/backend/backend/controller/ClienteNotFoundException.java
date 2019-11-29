package backend.backend.controller;

public class ClienteNotFoundException extends RuntimeException {

	ClienteNotFoundException(Long id) {
		super("Could not find cliente " + id);
	}

}
