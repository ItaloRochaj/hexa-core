package arqui.hexa_core.adapters.outbound.repositories;

import arqui.hexa_core.adapters.outbound.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository  extends JpaRepository <ClienteEntity, Long> {

}
