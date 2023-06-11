package br.edu.ifsc.chamados.models.call;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "call_type")
public class CallType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setor_id")
    private Setor setor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prioridade_id")
    private Prioritised prioridade;
    @Column(unique = true, nullable = false)
    private String descricao;

}
