package arqui.hexa_core.services;

import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.outbound.ClienteRepositoryPort;

import java.util.Optional;

public class ClienteServiceImpl {
    private final ClienteRepositoryPort clienteRepository;

    public ClienteServiceImpl(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank())
            throw new IllegalArgumentException("Nome obrigatório");
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
}
