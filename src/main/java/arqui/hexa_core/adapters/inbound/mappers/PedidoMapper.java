package arqui.hexa_core.adapters.inbound.mappers;

import arqui.hexa_core.adapters.inbound.dtos.PedidoDTO;
import arqui.hexa_core.core.domain.Pedido;

public class PedidoMapper {

    public static Pedido toDomain(PedidoDTO dto) {
        if (dto == null) return null;
        Pedido p = new Pedido();
        p.setId(dto.getId());
        p.setClienteId(dto.getClienteId());
        p.setValor(dto.getValor());
        return p;
    }

    public static PedidoDTO toDto(Pedido p) {
        if (p == null) return null;
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setClienteId(p.getClienteId());
        dto.setValor(p.getValor());
        return dto;
    }
}