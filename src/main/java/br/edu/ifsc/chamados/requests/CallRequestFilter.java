package br.edu.ifsc.chamados.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallRequestFilter implements Serializable {

    @DateTimeFormat(style = "dd/mm/yyyy")
    private LocalDate inicio;
    @DateTimeFormat(style = "dd/mm/yyyy")
    private LocalDate fim;
    private List<Integer> sectors;
    private List<Integer> priorities;
    private List<Integer> categories;
    private List<Integer> status;
    private String requester;
    private String solver;

}
