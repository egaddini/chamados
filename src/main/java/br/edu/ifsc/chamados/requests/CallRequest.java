package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallRequest {

    private Integer solicitante;
    private Integer callType;
    private String descricao;

}
