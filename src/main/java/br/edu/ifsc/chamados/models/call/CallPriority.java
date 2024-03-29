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
@Table(name = "call_priority")
public class CallPriority implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final String ID = "id";
    private String name;
    private Integer weight;
    public static final String WEIGHT = "weight";
    @JsonIgnore
    @OneToMany(mappedBy = "priority", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CallCategory> callCategories;
}
