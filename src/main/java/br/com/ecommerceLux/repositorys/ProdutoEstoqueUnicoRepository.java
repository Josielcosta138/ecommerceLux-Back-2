package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.Produto;
import br.com.ecommerceLux.entitys.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProdutoEstoqueUnicoRepository extends JpaRepository<ProdutoEstoque, Long>{
    Optional<ProdutoEstoque> findByProdutoId(Long produtoId);
}




