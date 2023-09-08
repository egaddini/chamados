package br.edu.ifsc.chamados.response.user;

import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.auth.Token;
import br.edu.ifsc.chamados.models.call.CallSector;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.models.user.UserSector;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    private Set<CallSector> sectors;

}
