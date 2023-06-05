package br.edu.ifsc.chamados.models.call;

import br.edu.ifsc.chamados.enums.CallStatus;
import br.edu.ifsc.chamados.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_call")
public class Call {

    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private LocalDateTime dataCriacao;
    @Column(unique = true, nullable = false)
    private LocalDate dataUltAtualizacao;
    @Enumerated(EnumType.STRING)
    private CallStatus status;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(unique = true, nullable = false)
    private User solicitante;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(unique = true, nullable = false)
    private User responsavel;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(unique = true, nullable = false)
    private CallType callType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "call")
    @Column(unique = true, nullable = false)
    private List<User> participantes;
    @Column(unique = true, nullable = false)
    private String descricao;
    @Column(unique = true, nullable = false)
    private String historico;

}
