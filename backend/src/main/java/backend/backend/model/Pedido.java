package backend.backend.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private LocalDate dataCriacao = LocalDate.now();

	@ManyToOne
	private Cliente cliente;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itempedido;

	public Pedido() {

	}

	public Pedido(Cliente cliente, List<ItemPedido> itempedido) {
		this.cliente = cliente;
		this.itempedido = itempedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return dataCriacao;
	}

	public void setData(LocalDate data) {
		this.dataCriacao = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItempedido() {
		return itempedido;
	}

	public void setItempedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}

}
