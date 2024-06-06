package br.com.ecommerceLux.repositorys;

import br.com.ecommerceLux.entitys.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {
}
