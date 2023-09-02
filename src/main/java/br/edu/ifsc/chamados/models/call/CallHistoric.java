package br.edu.ifsc.chamados.models.call;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "call_historic")
public class CallHistoric implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ocurrence_dt")
    private LocalDateTime ocurrenceDT;
    private String user;
    private String message;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "call_id")
    private Call call;
}