package br.edu.ifsc.chamados.response.user;

import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.auth.Token;
import br.edu.ifsc.chamados.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String email;
    private String nome;
    private String sobrenome;
    private Long telefone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
    private Boolean habilitado;
    private LocalDateTime dataCriacao;

}
