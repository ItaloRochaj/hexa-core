package arqui.hexa_core.services;

import arqui.hexa_core.adapters.inbound.dtos.PedidoDTO;
import arqui.hexa_core.adapters.inbound.mappers.PedidoMapper;
import arqui.hexa_core.core.domain.Pedido;
import arqui.hexa_core.core.ports.inbound.PedidoServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl {

    private final PedidoServicePort useCase;

    public PedidoServiceImpl(PedidoServicePort useCase) {
        this.useCase = useCase;
    }

    public PedidoDTO criar(PedidoDTO dto) {
        Pedido p = PedidoMapper.toDomain(dto);
        Pedido saved = useCase.criar(p);
        return PedidoMapper.toDto(saved);
    }

    public List<PedidoDTO> listar() {
        return useCase.listarTodos().stream().map(PedidoMapper::toDto).collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(Long id) {
        return useCase.buscarPorId(id).map(PedidoMapper::toDto).orElse(null);
    }

    public PedidoDTO atualizar(Long id, PedidoDTO dto) {
        Pedido p = PedidoMapper.toDomain(dto);
        p.setId(id);
        Pedido updated = useCase.atualizar(p);
        return PedidoMapper.toDto(updated);
    }

    public void deletar(Long id) { useCase.deletar(id); }
}