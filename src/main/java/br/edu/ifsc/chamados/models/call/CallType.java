package br.edu.ifsc.chamados.models.call;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_call_type")
public class CallType {

    @Id
    private Integer id;
    @Column(unique = true, nullable = false)
    private String sigla;
    @Column(unique = true, nullable = false)
    private String setor;
    @Column(unique = true, nullable = false)
    private String titulo;
    @Column(unique = true, nullable = false)
    private String prioridade;
    @Column(unique = true, nullable = false)
    private String descricao;

}
