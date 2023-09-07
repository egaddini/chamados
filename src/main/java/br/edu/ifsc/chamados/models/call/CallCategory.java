package br.edu.ifsc.chamados.models.call;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    @Column(unique = true, nullable = false)
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sector_id")
    private CallSector sector;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id")
    private CallPriority priority;
    @Column(unique = true, nullable = false)
    private String description;

}
