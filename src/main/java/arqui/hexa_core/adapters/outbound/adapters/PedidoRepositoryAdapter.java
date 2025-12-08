package arqui.hexa_core.adapters.outbound.adapters;

import arqui.hexa_core.adapters.outbound.entities.PedidoEntity;
import arqui.hexa_core.adapters.outbound.repositories.PedidoJpaRepository;
import arqui.hexa_core.core.domain.Pedido;
import arqui.hexa_core.core.ports.outbound.PedidoRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpa;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity e = toEntity(pedido);
        PedidoEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public List<Pedido> findAll() {
        return jpa.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    private Pedido toDomain(PedidoEntity e) {
        if (e == null) return null;
        return new Pedido(e.getId(), e.getClienteId(), e.getValor(), e.getStatus(), e.getCriadoEm());
    }

    private PedidoEntity toEntity(Pedido p) {
        if (p == null) return null;
        PedidoEntity e = new PedidoEntity();
        e.setId(p.getId());
        e.setClienteId(p.getClienteId());
        e.setValor(p.getValor());
        e.setStatus(p.getStatus());
        e.setCriadoEm(p.getCriadoEm());
        return e;
    }
}

