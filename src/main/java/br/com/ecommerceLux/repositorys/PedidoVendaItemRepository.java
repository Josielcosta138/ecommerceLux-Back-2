package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.PedidoVendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoVendaItemRepository extends JpaRepository<PedidoVendaItem, Long> {
}
