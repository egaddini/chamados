package br.edu.ifsc.chamados.models.call;

import br.edu.ifsc.chamados.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CALL")
public class Call implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CREATION_DT", nullable = false)
    private LocalDateTime creationDT;
    @Column(name = "LAST_UPDATE_DT", nullable = false)
    private LocalDateTime lastUpdateDT;
    @JoinColumn(name = "STATUS_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private Status status;
    @JoinColumn(name = "REQUESTER_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private User requester;
    @JoinColumn(name = "SOLVER_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private User solver;
    @JoinColumn(name = "CALL_CATEGORY_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private CallCategory callCategory;
//    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<CallParticipants> callParticipants;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Historic> historico;

}
