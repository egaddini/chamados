package br.edu.ifsc.chamados.models.call;

import br.edu.ifsc.chamados.enums.CallStatus;
import br.edu.ifsc.chamados.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime dataUltAtualizacao;
    @Enumerated(EnumType.STRING)
    private CallStatus status;
    @JoinColumn(name = "solicitante_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User solicitante;
    @JoinColumn(name = "responsavel_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User responsavel;
    @JoinColumn(name = "call_type_id")
    @OneToOne(cascade = CascadeType.ALL)
    private CallType callType;
    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<CallParticipants> callParticipants;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String historico;

}
