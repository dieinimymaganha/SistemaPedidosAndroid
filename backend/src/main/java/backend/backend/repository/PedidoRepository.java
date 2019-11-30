package backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
