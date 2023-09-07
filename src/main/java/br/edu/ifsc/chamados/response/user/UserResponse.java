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
    private String firstName;
    private String lastName;
    private Long phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
    private Boolean active;
    private LocalDateTime creationDT;

}
