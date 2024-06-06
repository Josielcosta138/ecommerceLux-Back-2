package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long> {
}
