package br.edu.ifsc.chamados.models.call;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HISTORIC")
public class Historic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "OCURRENCE_DT")
    private LocalDateTime ocurrenceDT;
    @Column(name = "USER")
    private String user;
    @Column(name = "MESSAGE")
    private String message;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CALL_ID")
    private Call call;
}