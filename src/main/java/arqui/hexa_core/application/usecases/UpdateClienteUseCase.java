package arqui.hexa_core.application.usecases;

import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.inbound.ClienteServicePort;
import arqui.hexa_core.core.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Cliente> listarTodos() { return clienteRepository.findAll(); }

    @Override
    public Cliente atualizar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) throw new IllegalArgumentException("id do cliente é obrigatório");
        if (cliente.getNome() == null || cliente.getNome().isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        // garante que exista
        clienteRepository.findById(cliente.getId()).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        // garante que exista antes de deletar
        clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }
}