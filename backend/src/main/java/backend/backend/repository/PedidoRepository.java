package backend.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByCliente_Id(Long cliente_id);

}
