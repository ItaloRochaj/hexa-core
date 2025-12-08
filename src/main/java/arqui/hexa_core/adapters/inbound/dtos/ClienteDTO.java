package arqui.hexa_core.adapters.inbound.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @Email(message = "email inválido")
    private String email;

    private String telefone;

    public ClienteDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
