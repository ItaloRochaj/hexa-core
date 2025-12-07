package arqui.hexa_core.core.ports.outbound;

import arqui.hexa_core.core.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido save(Pedido pedido);
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
}
