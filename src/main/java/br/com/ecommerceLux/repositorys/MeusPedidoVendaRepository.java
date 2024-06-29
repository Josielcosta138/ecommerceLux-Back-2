package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeusPedidoVendaRepository extends JpaRepository<PedidoVenda, Long> {
    List<PedidoVenda> findByClientesId(Long clientId);
}
