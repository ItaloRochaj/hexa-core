package arqui.hexa_core.application.usecases;

import arqui.hexa_core.core.domain.Pedido;
import arqui.hexa_core.core.ports.inbound.PedidoServicePort;
import arqui.hexa_core.core.ports.outbound.PedidoRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CreatePedidoUseCase implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepository;

    public CreatePedidoUseCase(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        if (pedido == null) throw new IllegalArgumentException("Pedido não pode ser nulo");
        if (pedido.getClienteId() == null) throw new IllegalArgumentException("clienteId obrigatório");
        if (pedido.getValor() == null || pedido.getValor().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("valor inválido");

        // regra simples de desconto: >=100 => 10% off
        if (pedido.getValor().compareTo(BigDecimal.valueOf(100)) >= 0) {
            BigDecimal desconto = pedido.getValor().multiply(BigDecimal.valueOf(0.10));
            pedido.setValor(pedido.getValor().subtract(desconto));
        }

        pedido.setStatus("CRIADO");
        pedido.setCriadoEm(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarTodos() { return pedidoRepository.findAll(); }

    @Override
    public Optional<Pedido> buscarPorId(Long id) { return pedidoRepository.findById(id); }
}
