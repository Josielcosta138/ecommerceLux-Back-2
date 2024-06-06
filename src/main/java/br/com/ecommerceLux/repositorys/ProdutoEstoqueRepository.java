package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Long> {
}
