package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallTypeRequest {

    private String title;
    private Integer sectorId;
    private Integer priorityId;
    private String description;

}
