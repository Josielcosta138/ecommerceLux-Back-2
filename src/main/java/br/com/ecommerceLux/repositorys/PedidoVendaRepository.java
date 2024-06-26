package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long> {

}
