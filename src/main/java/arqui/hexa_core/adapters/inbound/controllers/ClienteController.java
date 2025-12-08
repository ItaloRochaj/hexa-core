package arqui.hexa_core.adapters.inbound.controllers;

import arqui.hexa_core.adapters.inbound.dtos.ClienteDTO;
import arqui.hexa_core.core.domain.Cliente;
import arqui.hexa_core.core.ports.inbound.ClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServicePort clienteService;

    public ClienteController(ClienteServicePort clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody @Valid ClienteDTO dto) {
        Cliente domain = toDomain(dto);
        Cliente salvo = clienteService.criar(domain);
        ClienteDTO resp = toDto(salvo);
        return ResponseEntity.created(URI.create("/api/clientes/" + salvo.getId())).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // mapeamentos simples
    private Cliente toDomain(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setTelefone(dto.getTelefone());
        return c;
    }

    private ClienteDTO toDto(Cliente c) {
        if (c == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone());
        return dto;
    }
}
