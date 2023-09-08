package br.edu.ifsc.chamados.requests;

import br.edu.ifsc.chamados.models.call.CallSector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private Long phone;
    private String email;
    private String password;
    private Set<CallSector> sectors;

}