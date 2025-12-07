package arqui.hexa_core.core.ports.inbound;

import arqui.hexa_core.core.domain.Cliente;

import java.util.Optional;

public interface ClienteServicePort {
    Cliente criar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
}
