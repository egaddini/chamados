package br.edu.ifsc.chamados.models.call;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "call_status")
public class  CallStatus implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer weight;
    private boolean notify;
    @JsonIgnore
    @OneToMany(mappedBy = "status", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Call> calls;
}
