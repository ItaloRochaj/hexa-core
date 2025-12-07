package arqui.hexa_core.services;

import arqui.hexa_core.core.domain.Pedido;
import arqui.hexa_core.core.ports.outbound.PedidoRepositoryPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PedidoServiceImpl {
    private final PedidoRepositoryPort pedidoRepository;

    public PedidoServiceImpl(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criar(Pedido pedido) {
        if (pedido.getClienteId() == null) throw new IllegalArgumentException("clienteId obrigatório");
        if (pedido.getValor() == null || pedido.getValor().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("valor inválido");

        if (pedido.getValor().compareTo(BigDecimal.valueOf(100)) >= 0) {
            var desconto = pedido.getValor().multiply(BigDecimal.valueOf(0.10));
            pedido.setValor(pedido.getValor().subtract(desconto));
        }

        pedido.setStatus("CRIADO");
        pedido.setCriadoEm(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() { return pedidoRepository.findAll(); }

    public Optional<Pedido> buscarPorId(Long id) { return pedidoRepository.findById(id); }
}