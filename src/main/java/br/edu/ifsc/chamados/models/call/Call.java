package br.edu.ifsc.chamados.models.call;

import br.edu.ifsc.chamados.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_call")
public class Call implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime dataUltAtualizacao;
    @JoinColumn(name = "status_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Status status;
    @JoinColumn(name = "solicitante_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User solicitante;
    @JoinColumn(name = "responsavel_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User responsavel;
    @JoinColumn(name = "call_type_id")
    @OneToOne(cascade = CascadeType.ALL)
    private CallType callType;
//    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<CallParticipants> callParticipants;
    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Historic> historico;

}
