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
@Table(name = "`CALL`")
public class Call implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "creation_dt", nullable = false)
    private LocalDateTime creationDT;
    @Column(name = "last_update_dt", nullable = false)
    private LocalDateTime lastUpdateDT;
    @JoinColumn(name = "status_id")
    @ManyToOne
    private CallStatus status;
    @JoinColumn(name = "requester_id")
    @ManyToOne
    private User requester;
    @JoinColumn(name = "solver_id")
    @ManyToOne
    private User solver;
    @JoinColumn(name = "call_category_id")
    @ManyToOne
    private CallCategory callCategory;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "call", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CallHistoric> historic;

}
