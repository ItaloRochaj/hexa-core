package arqui.hexa_core.adapters.outbound.repositories;

import arqui.hexa_core.adapters.outbound.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository <PedidoEntity, Long> {
}
