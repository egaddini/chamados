package br.edu.ifsc.chamados.response.auth;

import br.edu.ifsc.chamados.api.models.user.IUser;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.response.user.UserResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
}
