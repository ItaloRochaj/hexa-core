package arqui.hexa_core.adapters.inbound.mappers;

import arqui.hexa_core.adapters.inbound.dtos.ClienteDTO;
import arqui.hexa_core.core.domain.Cliente;

public class ClienteMapper {

    public static Cliente toDomain(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setTelefone(dto.getTelefone());
        return c;
    }

    public static ClienteDTO toDto(Cliente c) {
        if (c == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone());
        return dto;
    }
}

