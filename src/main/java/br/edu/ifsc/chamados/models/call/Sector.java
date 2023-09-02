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
@Table(name = "SECTOR")
public class Sector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ACRONYM", unique = true, nullable = false, length = 3)
    private String acronym;
    @Column(name = "NAME" ,unique = true, nullable = false)
    private String name;

}
