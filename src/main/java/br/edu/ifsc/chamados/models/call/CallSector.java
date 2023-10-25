package br.edu.ifsc.chamados.models.call;

import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.models.user.UserSector;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "call_sector")
public class CallSector implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 3)
    private String acronym;
    @Column(unique = true, nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "sector", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserSector> userSector;

    @JsonIgnore
    @OneToMany(mappedBy = "sector", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CallCategory> callCategories;

}
