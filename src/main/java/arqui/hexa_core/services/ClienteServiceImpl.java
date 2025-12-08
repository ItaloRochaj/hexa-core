package arqui.hexa_core.services;

import arqui.hexa_core.adapters.inbound.dtos.ClienteDTO;
import arqui.hexa_core.application.usecases.UpdateClienteUseCase;
import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.inbound.ClienteServicePort;
import arqui.hexa_core.adapters.inbound.mappers.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl {

    private final ClienteServicePort useCase;

    public ClienteServiceImpl(ClienteServicePort useCase) {
        this.useCase = useCase;
    }

    public ClienteDTO criar(ClienteDTO dto) {
        Cliente c = ClienteMapper.toDomain(dto);
        Cliente saved = useCase.criar(c);
        return ClienteMapper.toDto(saved);
    }

    public List<ClienteDTO> listar() {
        return useCase.listarTodos().stream().map(ClienteMapper::toDto).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        return useCase.buscarPorId(id).map(ClienteMapper::toDto).orElse(null);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente c = ClienteMapper.toDomain(dto);
        c.setId(id);
        Cliente updated = useCase.atualizar(c);
        return ClienteMapper.toDto(updated);
    }

    public void deletar(Long id) { useCase.deletar(id); }
}
