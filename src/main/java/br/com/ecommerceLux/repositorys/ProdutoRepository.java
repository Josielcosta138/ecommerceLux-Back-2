package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
