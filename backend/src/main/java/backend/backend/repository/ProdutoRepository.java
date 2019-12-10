package backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import backend.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Produto findByDescricao(String descricaoProduto);


}
