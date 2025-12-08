package arqui.hexa_core.adapters.outbound.adapters;

import arqui.hexa_core.adapters.outbound.entities.ClienteEntity;
import arqui.hexa_core.adapters.outbound.repositories.ClienteJpaRepository;
import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {
    private final ClienteJpaRepository jpa;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity e = toEntity(cliente);
        ClienteEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return jpa.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    private Cliente toDomain(ClienteEntity e) {
        if (e == null) return null;
        return new Cliente(e.getId(), e.getNome(), e.getEmail(), e.getTelefone());
    }

    private ClienteEntity toEntity(Cliente c) {
        if (c == null) return null;
        ClienteEntity e = new ClienteEntity();
        e.setId(c.getId());
        e.setNome(c.getNome());
        e.setEmail(c.getEmail());
        e.setTelefone(c.getTelefone());
        return e;
    }
}