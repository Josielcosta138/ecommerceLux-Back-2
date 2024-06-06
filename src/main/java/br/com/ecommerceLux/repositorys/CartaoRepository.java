package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
