package backend.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCpf(String cpf);

}
