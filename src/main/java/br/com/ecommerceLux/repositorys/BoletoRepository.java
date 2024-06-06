package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
