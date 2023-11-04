package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallRequestFilter implements Serializable {

    @DateTimeFormat(style = "dd/mm/yyyy")
    private LocalDate inicio;
    @DateTimeFormat(style = "dd/mm/yyyy")
    private LocalDate fim;
    private Integer statusWeight;
    private String requesterEmail = "";
    private String solverEmail;
    private Integer callCategoryID;
    private String description = "";
    private Integer priorityID;

}
