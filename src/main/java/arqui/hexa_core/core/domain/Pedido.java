package arqui.hexa_core.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Pedido {
    private Long id;
    private Long clienteId;
    private BigDecimal valor;
    private String status;
    private LocalDateTime criadoEm;

    public Pedido() {}

    public Pedido(Long id, Long clienteId, BigDecimal valor, String status, LocalDateTime criadoEm) {
        this.id = id;
        this.clienteId = clienteId;
        this.valor = valor;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
