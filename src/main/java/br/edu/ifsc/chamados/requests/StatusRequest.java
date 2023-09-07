package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Integer weight;
    private boolean notify;
}
