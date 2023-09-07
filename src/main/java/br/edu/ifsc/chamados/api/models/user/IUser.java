package br.edu.ifsc.chamados.api.models.user;

import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.auth.Token;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.List;

public interface IUser extends Serializable {

    Integer getId();
    String getFirstname();
    String getLastname();
    String getEmail();
    Role getRole();
}
