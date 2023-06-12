package br.edu.ifsc.chamados.response.call;

import br.edu.ifsc.chamados.enums.CallStatus;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.response.user.UserResponse;
import br.edu.ifsc.chamados.response.user.UserTinyResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallResponse {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltAtualizacao;
    @Enumerated(EnumType.STRING)
    private CallStatus status;
    private UserTinyResponse solicitante;
    private UserTinyResponse responsavel;
    private CallType callType;
    private String descricao;
    private String historico;

}
