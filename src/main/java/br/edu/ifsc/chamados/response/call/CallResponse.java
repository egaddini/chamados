package br.edu.ifsc.chamados.response.call;

import br.edu.ifsc.chamados.models.call.CallCategory;
import br.edu.ifsc.chamados.models.call.CallHistoric;
import br.edu.ifsc.chamados.response.user.UserTinyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private LocalDateTime creationDT;
    private LocalDateTime lastUpdateDT;
    private String status;
    private UserTinyResponse requester;
    private UserTinyResponse solver;
    private CallCategory callCategory;
    private String description;
    private List<CallHistoric> historic;

}
