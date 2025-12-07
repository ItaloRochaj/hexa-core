package arqui.hexa_core.application.usecases;

import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.inbound.ClienteServicePort;
import arqui.hexa_core.core.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClienteUseCase implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepository;

    public UpdateClienteUseCase(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        // Reaproveitado criar para persistir novo cliente ( separar, crie Create/Update)
        if (cliente.getNome() == null || cliente.getNome().isBlank())
            throw new IllegalArgumentException("Nome obrigatório");
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) { return clienteRepository.findById(id); }
}