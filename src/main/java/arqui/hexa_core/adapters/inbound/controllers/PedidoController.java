package arqui.hexa_core.adapters.inbound.controllers;

import arqui.hexa_core.adapters.inbound.dtos.PedidoDTO;
import arqui.hexa_core.core.domain.Pedido;
import arqui.hexa_core.core.ports.inbound.PedidoServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoServicePort pedidoService;

    public PedidoController(PedidoServicePort pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@RequestBody @Valid PedidoDTO dto) {
        Pedido domain = toDomain(dto);
        Pedido salvo = pedidoService.criar(domain);
        PedidoDTO resp = toDto(salvo);
        return ResponseEntity.created(URI.create("/api/pedidos/" + salvo.getId())).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // mapeamentos
    private Pedido toDomain(PedidoDTO dto) {
        if (dto == null) return null;
        Pedido p = new Pedido();
        p.setId(dto.getId());
        p.setClienteId(dto.getClienteId());
        p.setValor(dto.getValor());
        return p;
    }

    private PedidoDTO toDto(Pedido p) {
        if (p == null) return null;
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setClienteId(p.getClienteId());
        dto.setValor(p.getValor());
        return dto;
    }
}
