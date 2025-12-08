package arqui.hexa_core.adapters.inbound.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PedidoDTO {

    private Long id;

    @NotNull(message = "clienteId é obrigatório")
    private Long clienteId;

    @NotNull(message = "valor é obrigatório")
    private BigDecimal valor;

    public PedidoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
