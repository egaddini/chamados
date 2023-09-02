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
@Table(name = "CALL_CATEGORY")
public class CallCategory  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TITLE", unique = true, nullable = false)
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SECTOR_ID")
    private Sector sector;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRIORITY_ID")
    private Priority priority;
    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    private String description;

}
