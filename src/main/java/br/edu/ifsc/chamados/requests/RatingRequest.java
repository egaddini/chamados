package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    private Integer callID;

    private Integer satisfaction;

    private Integer solveTime;

    private String feedback;

}
