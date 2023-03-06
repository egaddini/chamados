package br.edu.ifsc.chamados.models.user;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
public class UserDTOImpl implements Serializable {

    public static final Long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String password;

}
