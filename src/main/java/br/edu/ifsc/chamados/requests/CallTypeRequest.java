package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallTypeRequest {

    private String titulo;
    private Integer setorId;
    private Integer prioridadeId;
    private String descricao;

}
