package br.edu.ifsc.chamados.models.call;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CALL_RATING")
public class CallRating implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer satisfaction;

    @Column(name = "solve_time")
    private Integer solveTime;

    private String feedback;

    @JsonIgnore
    @OneToMany(mappedBy = "callRating", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Call> call;

}
