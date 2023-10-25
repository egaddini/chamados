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
@Table(name = "call_category")
public class CallCategory implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private CallSector sector;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private CallPriority priority;
    @Column(nullable = false)
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "callCategory", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Call> calls;

}
