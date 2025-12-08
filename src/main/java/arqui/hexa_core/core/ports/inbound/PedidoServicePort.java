package arqui.hexa_core.core.ports.inbound;

import arqui.hexa_core.core.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoServicePort {
    Pedido criar(Pedido pedido);
    List<Pedido> listarTodos();
    Optional<Pedido> buscarPorId(Long id);
    Pedido atualizar(Pedido pedido);
    void deletar(Long id);
}
