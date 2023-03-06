package br.edu.ifsc.chamados.models;

import br.edu.ifsc.chamados.enums.ERoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder()
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ERoleName roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

}
